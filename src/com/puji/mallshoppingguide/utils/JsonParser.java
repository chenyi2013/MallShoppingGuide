package com.puji.mallshoppingguide.utils;

import com.alibaba.fastjson.JSON;

public class JsonParser {

	public static <T> T getParsedData(String json, Class<T> cls) {
		return JSON.parseObject(json, cls);
	}

}
