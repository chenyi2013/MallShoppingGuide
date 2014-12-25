package com.puji.mallshoppingguide.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class FormatUtils {

	public static String formatParams(HashMap<String, String> params) {

		JSONObject json = new JSONObject();
		Iterator<String> iterator = params.keySet().iterator();

		try {
			while (iterator.hasNext()) {
				String key = iterator.next();
				json.put(key, params.get(key));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();

	}

}
