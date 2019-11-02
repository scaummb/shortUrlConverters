package com.core.constants;

/**
 * @author moubin.mo
 * @date: 2019/8/31 17:26
 */

public class RedisConstants {
	/** redis存储url的唯一seqId：key */
	public static final String SHORT_URL_SEQ_KEY = "short.url.seq";
	/** redis存储长链接、短链接以及seqId：公共key */
	public static final String STORE_URLS_AND_SEQ_KEY = "store.urls";

}
