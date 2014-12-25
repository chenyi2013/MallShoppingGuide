package com.puji.mallshoppingguide.bean;

import java.util.ArrayList;

public class Categorys {

	private int Status;
	private String Msg;
	ArrayList<Category> Data;

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public ArrayList<Category> getData() {
		return Data;
	}

	public void setData(ArrayList<Category> data) {
		Data = data;
	}

	@Override
	public String toString() {
		return "Categorys [Status=" + Status + ", Msg=" + Msg + ", Data="
				+ Data + "]";
	}

}
