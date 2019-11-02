package com.core.pojo.dto;

/**
 * @author moubin.mo
 * @date: 2019/9/1 17:05
 */

public class LongUrlDTO {
	private String longUrl;
	private Long urlId;

	public LongUrlDTO() {
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	@Override
	public String toString() {
		return "LongUrlDTO{" +
				"longUrl='" + longUrl + '\'' +
				", urlId=" + urlId +
				'}';
	}
}
