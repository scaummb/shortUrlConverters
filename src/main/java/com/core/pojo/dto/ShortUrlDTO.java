package com.core.pojo.dto;

/**
 * 短链接 DTO
 *
 * urlId:id
 * shortUrl：短链接
 *
 * @author moubin.mo
 * @date: 2019/8/31 15:04
 */

public class ShortUrlDTO {
	private Long urlId;
	private String shortUrl;

	public ShortUrlDTO() {
	}

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}
