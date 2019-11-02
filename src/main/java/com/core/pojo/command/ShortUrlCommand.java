package com.core.pojo.command;

/**
 * @author moubin.mo
 * @date: 2019/9/1 17:07
 */

public class ShortUrlCommand {
	private String shortUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@Override
	public String toString() {
		return "ShortUrlCommand{" +
				"shortUrl='" + shortUrl + '\'' +
				'}';
	}
}
