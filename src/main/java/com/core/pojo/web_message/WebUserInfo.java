package com.core.pojo.web_message;

/**
 * @author moubin.mo
 * @date: 2019/11/8 16:03
 */

public class WebUserInfo {
	private Integer namespaceId;
	private Long userId;

	public WebUserInfo(Integer namespaceId, Long userId) {
		this.namespaceId = namespaceId;
		this.userId = userId;
	}

	public Integer getNamespaceId() {
		return namespaceId;
	}

	public void setNamespaceId(Integer namespaceId) {
		this.namespaceId = namespaceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
