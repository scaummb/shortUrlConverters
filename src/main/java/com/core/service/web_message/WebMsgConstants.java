package com.core.service.web_message;

/**
 *	Check_TIME_INTERVAL：长连接check间隔
 *	VALID_TIME：长连接失效时间
 *	LONG_POLL_TIMEOUT：长连接hold时间
 *	LONG_POLL_START：是否开启长轮询
 *
 * @author moubin.mo
 * @date: 2019/11/7 15:14
 */

public class WebMsgConstants {
	public final static int Check_TIME_INTERVAL = 1 * 60 * 1000 ;
	public final static int VALID_TIME =  2 * 60 * 1000;
	public final static long LONG_POLL_TIMEOUT =  1 * 60 * 1000;
	public final static boolean LONG_POLL_START = true;

	public final static String NEXT_ANCHOR = "";
	public final static String STATUS = "status";
}
