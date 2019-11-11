package com.core.pojo.web_message;

/**
 * <ul>长轮询结果
 * <li>nextAnchor：消息anchor</li>
 * <li>status：{@link com.core.pojo.web_message.TrueOrFalseFlag}</li>
 * </ul>
 * @author moubin.mo
 * @date: 2019/11/7 17:43
 */

public class WebMessageLongPollingDTO {
	private long nextAnchor;
	private Byte status;

	public long getNextAnchor() {
		return nextAnchor;
	}

	public void setNextAnchor(long nextAnchor) {
		this.nextAnchor = nextAnchor;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}
