package com.puji.mallshoppingguide.bean;

public class Category {

	private String CategoryID;
	private String Name;
	private String EnglishName;
	private String Icon;
	private String IconPicPath;
	private String TypeID;

	public String getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEnglishName() {
		return EnglishName;
	}

	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}

	public String getIcon() {
		return Icon;
	}

	public void setIcon(String icon) {
		Icon = icon;
	}

	public String getIconPicPath() {
		return IconPicPath;
	}

	public void setIconPicPath(String iconPicPath) {
		IconPicPath = iconPicPath;
	}

	public String getTypeID() {
		return TypeID;
	}

	public void setTypeID(String typeID) {
		TypeID = typeID;
	}

	@Override
	public String toString() {
		return "Category [CategoryID=" + CategoryID + ", Name=" + Name
				+ ", EnglishName=" + EnglishName + ", Icon=" + Icon
				+ ", IconPicPath=" + IconPicPath + ", TypeID=" + TypeID + "]";
	}

}
