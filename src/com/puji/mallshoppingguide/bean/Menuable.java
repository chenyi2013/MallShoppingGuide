package com.puji.mallshoppingguide.bean;

import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

public abstract class Menuable {
	
	protected int mMenuId;
	
	public int getmMenuId() {
		return mMenuId;
	}

	public void setmMenuId(int mMenuId) {
		this.mMenuId = mMenuId;
	}

	public interface onMenuClickListener {
		public void onClick(int menuId);
	}

	public abstract void add(ViewGroup view, onMenuClickListener clickListener);

	public abstract void setMenuBackground(int resId);

	public abstract void setMenuBackground(Drawable drawable);
	
}
