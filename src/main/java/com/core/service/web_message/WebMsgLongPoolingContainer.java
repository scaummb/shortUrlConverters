package com.core.service.web_message;

import com.core.pojo.web_message.WebMessageLongPollingCommand;
import com.core.pojo.web_message.WebMessageLongPollingDTO;
import com.core.pojo.web_message.WebUserInfo;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.jooq.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 长轮询接口
 * @author moubin.mo
 * @date: 2019/11/7 14:56
 */
@Component
public class WebMsgLongPoolingContainer {

	private final Logger LOGGER = LoggerFactory.getLogger(WebMsgLongPoolingContainer.class);

	/** 监听轮询请求与移除请求的锁 */
	private ReentrantLock lock = new ReentrantLock();

	/** 单个定时任务线程池：监听长轮询并设置返回值 */
	private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	/** 轮训返回默认结果 */
	public static Map< String, String > DEFAULT_RESULT = new HashMap< String, String >() {{
		put( WebMsgConstants.NEXT_ANCHOR, "-1");
		put( WebMsgConstants.STATUS, "0" );
	}};

	/**
	 *  用户轮询请求池:guava中的Multimap，多值map,对map的增强，一个key可以保持多个value，这里用于维护某个用户的所有tab轮询请求
	 *  key = ns-uid，value = tabUid
	 */
	private static Multimap<String, String> userTabDeferredResultMap = Multimaps.synchronizedSetMultimap(HashMultimap.create());

	/**
	 * tab页面的轮询请求结果池，key = tabUid，value = DeferredResult
	 */
	private static Map<String, DeferredResult<WebMessageLongPollingDTO>> tabDeferredResultMap = new ConcurrentHashMap<>();

	/**
	 * 调度器监听轮询请求
	 * @param command 轮询参数
	 * @param requestTime 轮询请求时间戳
	 * @exception java.util.ConcurrentModificationException
	 */
	public DeferredResult<WebMessageLongPollingDTO> watch(WebMessageLongPollingCommand command, Long requestTime) {
		DeferredResult<WebMessageLongPollingDTO> deferredResult = new DeferredResult<>(WebMsgConstants.LONG_POLL_TIMEOUT, DEFAULT_RESULT);
		String userTabsMapKey = getUserTabsMapKey(command.getNamespaceId(), command.getUid());

		deferredResult.onCompletion( () -> {
			// 2.当deferredResult完成时（不论是超时还是异常还是正常完成），移除tabDeferredResultMap中相应的tabUid
			lock.lock();
			try {
//				LOGGER.info("complete|result:{}", JSONObject.toJSONString((Map<?, ?>) deferredResult.getResult()));
				tabDeferredResultMap.remove(command.getTabUid(), deferredResult);
			} finally {
				lock.unlock();
			}

		} );

		lock.lock();
		try {
			Collection<String> userTabs = userTabDeferredResultMap.get(userTabsMapKey);
			while (userTabs.iterator().hasNext()){
				String tab = userTabs.iterator().next();

			}
			// 设置tab页面的轮询请求结果池
			tabDeferredResultMap.put(command.getTabUid(), deferredResult);
		} finally {
			lock.unlock();
		}

		return deferredResult;
	}

	/**
	 * 获取key
	 */
	private String getUserTabsMapKey(Integer namespaceId, Long uid) {
		return StringUtils.join(new String[]{namespaceId.toString(), uid.toString()}, ":");
	}

	/**
	 * 提取key
	 */
	private WebUserInfo explainUserTabsMapKey(String userTabsMapKey) {
		String[] arrays = userTabsMapKey.split(":");
		return new WebUserInfo(Integer.valueOf(arrays[0]), Long.valueOf(arrays[1]));
	}

	/**
	 * 定时任务开启
	 */
	public WebMsgLongPoolingContainer() {
		WebmessageLongPollTask task = new WebmessageLongPollTask();
		executor.scheduleWithFixedDelay(task, 2, 1, TimeUnit.SECONDS);
	}

	/**
	 * 轮询任务：根据用户轮询连接池，对比redis的用户未读数据，设置长轮询连接结果
	 */
	private class WebmessageLongPollTask implements Runnable {

		@Override
		public void run() {
			// 1.对比用户长连接池与redis的数据
//			Iterator<String> iterator = userConnectPool.keySet().iterator();
//			while (iterator.hasNext()){
//				String namespaceIdUid = iterator.next();
//				WebUserInfo userTabsMapKey = explainUserTabsMapKey(namespaceIdUid);
//
//				// 2.设置轮询请求结果
//				Collection<String> userAllTabs = getUserAllTabs(userTabsMapKey.getUserId());
//
//				// 3.处理tab的轮询请求设置返回值
//				manageUserLongPoolingResult(userAllTabs);
//
//			}


		}
	}

	/**
	 * 处理tab的轮询请求设置返回值
	 * @param userAllTabs
	 */
	private void manageUserLongPoolingResult(Collection<String> userAllTabs) {
		while (userAllTabs.iterator().hasNext()){
			String tabUid = userAllTabs.iterator().next();
			long currentTime = System.currentTimeMillis();
			// todo 完善从redis取出数据并设置返回值
		}
	}

	/**
	 * 根据 userId 获取用户所有tab信息
	 */
	private Collection<String> getUserAllTabs(Long userId) {
		 return userTabDeferredResultMap.get(String.valueOf(userId));
	}
}
