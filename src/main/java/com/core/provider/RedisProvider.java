package com.core.provider;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author moubin.mo
 * @date: 2019/8/31 17:11
 */

public interface RedisProvider {
	Long getUrlSequnceId();

	Long saveSeqIdAndUrls(Long seqId, String shortUrl, String longUrl);

	String getLongUrl(String shortUrl, String urlId);

	RedisTemplate getRedisTemplate();
}
