package com.puji.mallshoppingguide.bean;

import java.util.ArrayList;

public class Businesses {

	private ArrayList<Business> Data;
	private int Status;
	private String Msg;

	public ArrayList<Business> getData() {
		return Data;
	}

	public void setData(ArrayList<Business> data) {
		Data = data;
	}

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

	@Override
	public String toString() {
		return "Businesses [Data=" + Data + ", Status=" + Status + ", Msg="
				+ Msg + "]";
	}

}
