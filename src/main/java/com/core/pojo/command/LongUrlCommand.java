package com.core.pojo.command;

import javax.validation.constraints.NotNull;

/**
 *
 * longUrl：长链接
 *
 * @author moubin.mo
 * @date: 2019/8/31 14:58
 */

public class LongUrlCommand {
	@NotNull
	private String longUrl;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	@Override
	public String toString() {
		return "LongUrlCommand{" +
				"longUrl='" + longUrl + '\'' +
				'}';
	}
}
