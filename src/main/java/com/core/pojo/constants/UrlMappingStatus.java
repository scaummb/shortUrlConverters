package com.core.pojo.constants;

/**
 * 链接状态
 * @author moubin.mo
 * @date: 2019/9/20 17:59
 */

public enum UrlMappingStatus {

	VALID((byte)1,"生效"),
	INVALID((byte)0,"无效"),
	;

	private Byte status;
	private String text;

	public Byte getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}

	UrlMappingStatus(Byte status, String text) {
		this.status = status;
		this.text = text;
	}
}
