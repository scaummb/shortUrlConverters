package com.core.provider;

import com.core.constants.RedisConstants;
import com.core.utils.redis.RedisTemplateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis 操作类
 *
 * @author moubin.mo
 * @date: 2019/8/31 17:12
 */

@Component
public class RedisProviderImpl implements RedisProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisProviderImpl.class);

	@Autowired
	private RedisTemplateFactory redisTemplateFactory;

	/**
	 * 从redis获取url唯一id
	 */
	@Override
	public Long getUrlSequnceId() {
		String seq = (String) getRedisTemplate().opsForValue().get(RedisConstants.SHORT_URL_SEQ_KEY);
		if (!StringUtils.isEmpty(seq)){
			return Long.valueOf(seq);
		} else {
			return 0L;
		}
	}

	/**
	 * 将长url与短url和seqId存储进redis
	 */
	@Override
	public Long saveSeqIdAndUrls(Long seqId, String shortUrl, String longUrl) {
		try {
			getRedisTemplate().opsForHash().put(RedisConstants.STORE_URLS_AND_SEQ_KEY + "." +getUrlBunketId(seqId), shortUrl, longUrl);
			getRedisTemplate().opsForValue().set(RedisConstants.SHORT_URL_SEQ_KEY, (++seqId).toString());
			return seqId;
		} catch (Exception e){
			LOGGER.error("Can not finish saveSeqIdAndUrls, seqId = {}", seqId, e);
		}
		return seqId;
	}
	/**
	 * 通过短链接与urlId，获取原来的长链接
	 */
	@Override
	public String getLongUrl(String shortUrl, String urlId) {
		try{
			String longUrl = (String) getRedisTemplate().opsForHash().get(RedisConstants.STORE_URLS_AND_SEQ_KEY + "." + getUrlBunketId(Long.valueOf(urlId)), shortUrl);
			if (!StringUtils.isEmpty(longUrl)){
				return longUrl;
			} else {
				LOGGER.error("Can not findout sepecific longUrl, seqId = {}, shortUrl = {}", urlId, shortUrl);
			}
		} catch (Exception e){
			LOGGER.error("Error happens when finding sepecific longUrl, seqId = {}, shortUrl = {}", urlId, shortUrl, e);
		}
		return null;
	}

	/**
	 * 获取存储urls的盒子id
	 */
	private Long getUrlBunketId(Long seqId) {
		long bucketId = 0L;
		if (seqId >= 0){
			bucketId = seqId >> 10;
		}
		return bucketId;
	}

	/**
	 * 从redisFactory获取redisTemplate
	 */
	@Override
	public RedisTemplate getRedisTemplate(){
		return redisTemplateFactory.getRedisTemplate();
	}
}
