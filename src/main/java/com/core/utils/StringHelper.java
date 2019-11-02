package com.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author moubin.mo
 * @date: 2019/11/1 12:26
 */

public class StringHelper {
	private final static Gson gson = new GsonBuilder().create();

	public static String toJsonString(Object object){
		return gson.toJson(object);
	}
}
