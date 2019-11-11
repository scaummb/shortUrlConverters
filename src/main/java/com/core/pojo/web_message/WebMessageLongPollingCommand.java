package com.core.pojo.web_message;

import javax.validation.constraints.NotNull;

/**
 * <ul>长轮询结果
 * <li>namespaceId:  域空间id</li>
 * <li>uid: 用户id</li>
 * <li>tabUid: 标签页id</li>
 * </ul>
 * @author moubin.mo
 * @date: 2019/11/7 14:50
 */

public class WebMessageLongPollingCommand {
	@NotNull
	private Long uid;
	@NotNull
	private Integer namespaceId;
	@NotNull
	private String tabUid;

	public String getTabUid() {
		return tabUid;
	}

	public void setTabUid(String tabUid) {
		this.tabUid = tabUid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getNamespaceId() {
		return namespaceId;
	}

	public void setNamespaceId(Integer namespaceId) {
		this.namespaceId = namespaceId;
	}
}
