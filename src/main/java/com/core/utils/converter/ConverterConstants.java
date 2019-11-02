package com.core.utils.converter;

/**
 * MD5加密算法常量
 *
 * @author moubin.mo
 * @date: 2019/8/31 16:24
 */

public class ConverterConstants {
	/** 短链接可选字符 */
	public final static char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

	/** md5的char值 */
	public final static char[] MD5_CHARS = "1234567890EADCBF".toCharArray();

	/** 30位的long类型数值,右移运算可以达30 */
	public static long MAX = Long.parseLong("3FFFFFFF", 16);

	/** 61的最大值以便于从chars中选择字符 */
	public static long MIN = Long.parseLong("0000003D", 16);

	/** 要使用生成短URL的字符 */
	public final static String[] SHORT_URL_CHARS = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
			"i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
			"u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
			"6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
			"I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
			"U" , "V" , "W" , "X" , "Y" , "Z"
	};

	/** 可以自定义生成 MD5 加密字符传前的混合 KEY */
	public static String KEY = "shortUrlConverter" ;

}
