package com.puji.mallshoppingguide.bean;

public class Business {
	private String BusinessID;
	private String LogoPath;
	private String BusinessName;
	private String Position;
	private String BusinessNumber;
	private String Tag;
	private String Info;

	public String getBusinessID() {
		return BusinessID;
	}

	public void setBusinessID(String businessID) {
		BusinessID = businessID;
	}

	public String getLogoPath() {
		return LogoPath;
	}

	public void setLogoPath(String logoPath) {
		LogoPath = logoPath;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getBusinessNumber() {
		return BusinessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		BusinessNumber = businessNumber;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public String getInfo() {
		return Info;
	}

	public void setInfo(String info) {
		Info = info;
	}

	@Override
	public String toString() {
		return "Business [BusinessID=" + BusinessID + ", LogoPath=" + LogoPath
				+ ", BusinessName=" + BusinessName + ", Position=" + Position
				+ ", BusinessNumber=" + BusinessNumber + ", Tag=" + Tag
				+ ", Info=" + Info + "]";
	}

}
