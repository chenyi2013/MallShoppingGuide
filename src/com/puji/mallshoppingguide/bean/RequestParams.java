package com.puji.mallshoppingguide.bean;

public class RequestParams {

	private String requestBody;
	private int method;
	private String url;
	private Class<?> cls;

	public RequestParams(int method, String url, String requestBody,
			Class<?> cls) {
		super();
		this.requestBody = requestBody;
		this.method = method;
		this.url = url;
		this.cls = cls;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

}
