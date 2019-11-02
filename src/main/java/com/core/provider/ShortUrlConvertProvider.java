package com.core.provider;

/**
 * @author moubin.mo
 * @date: 2019/8/31 15:44
 */

public interface ShortUrlConvertProvider {

	/** 获取urlId */
	Long getUrlSequnceId();

	/** 存储id与长短urls进redis */
	Long saveSeqIdAndUrls(Long seqId, String shortUrl, String longUrl);

	/** 获取长url */
	String getLongUrl(String shortUrl, String urlId);

	Long saveShortUrl(Long seqId, String shortUrl, String longUrl);

}
