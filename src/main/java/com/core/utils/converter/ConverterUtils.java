package com.core.utils.converter;

import com.core.constants.CoreConstants;

/**
 * 短链接工具类
 *
 * @author: mmb
 * @date: 19-9-10
 */
public class ConverterUtils {

    /**
     * 创建重定向url
     */
    public static String buildRedirectUrl(String url){

        if (url.startsWith("http://") || url.startsWith("https://")){
            return url;
        } else {
            return CoreConstants.REDIRECT_URL_HEAD + url;
        }

    }
}
