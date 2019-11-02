package com.core.kafka;

import com.core.utils.StringHelper;

import java.util.Date;

/**
 * @author moubin.mo
 * @date: 2019/11/1 12:22
 */

public class KafkaMessage {
	private Long id;
	private String msg;
	private Date sendTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Override
	public String toString() {
		return StringHelper.toJsonString(this);
	}
}
