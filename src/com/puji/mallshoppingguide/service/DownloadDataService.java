package com.puji.mallshoppingguide.service;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.puji.event.EventBus;
import com.puji.mallshoppingguide.application.MallShoppingGuideApplication;
import com.puji.mallshoppingguide.bean.Categorys;
import com.puji.mallshoppingguide.bean.RequestParams;
import com.puji.mallshoppingguide.cofig.Constants;
import com.puji.mallshoppingguide.utils.FormatUtils;
import com.puji.mallshoppingguide.utils.JsonParser;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class DownloadDataService extends Service {

	private RequestQueue mRequestQueue;
	private MallShoppingGuideApplication mApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = (MallShoppingGuideApplication) getApplication();
		mRequestQueue = mApplication.getRequestQueue();
		EventBus.getDefault().register(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		HashMap<String, String> params = new HashMap<>();
		params.put(Constants.HOME_KEY, Constants.HOME_VALUE);
		onEventMainThread(new RequestParams(Method.POST, Constants.HOME_URL,
				FormatUtils.formatParams(params), Categorys.class));

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void onEventMainThread(final RequestParams tag) {

		JSONObject json = null;
		try {
			json = new JSONObject(tag.getRequestBody());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mRequestQueue.add(new JsonObjectRequest(tag.getMethod(), tag.getUrl(),
				json, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						EventBus.getDefault().post(
								JsonParser.getParsedData(response.toString(),
										tag.getCls()));
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						Toast.makeText(mApplication, "load data error",
								Toast.LENGTH_LONG).show();

					}
				}));

	}

}
