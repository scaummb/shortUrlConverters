package com.core.utils.converter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author moubin.mo
 * @date: 2019/8/31 15:52
 */

public class ShortUrlGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShortUrlGenerator.class);

	/**
	 * 长链接转为短链接
	 * @param longUrl
	 * @return String
	 */
	public static String generateShortUrl(String longUrl, Long urlId) {

		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = md5(ConverterConstants.KEY + longUrl + String.valueOf(urlId));
		String hex = sMD5EncryptResult;
		StringBuffer result = new StringBuffer();

		for ( int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);

			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);

			String outChars = "" ;

			for ( int j = 0; j < 6; j++) {

				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;

				// 把取得的字符相加
				outChars += ConverterConstants.SHORT_URL_CHARS[( int ) index];

				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}

			// 把字符串存入对应索引的输出数组
			result.append(outChars);
		}
		return result.toString();
	}

	/**
	 * MD5字符串加密算法
	 * @param string
	 * @return String
	 */
	public final static String md5(String string) {
		try{
			byte[] btInput = string.getBytes();

			/** 获得MD5摘要算法的 MessageDigest 对象 */
			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			/** 使用指定的字节更新摘要 */
			mdInst.update(btInput);

			/** 获得密文  */
			byte[] md = mdInst.digest();

			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = ConverterConstants.MD5_CHARS[byte0 >>> 4 & 0xf];
				str[k++] = ConverterConstants.MD5_CHARS[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e){
			LOGGER.error("Generate short");
			return null;
		}
	}
}
