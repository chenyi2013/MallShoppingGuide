package com.puji.mallshoppingguide.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.puji.mallshoppingguide.R;

public class NormalMenu extends Menuable {

	private String menuZnTitle;
	private String menuEnTitle;
	private Context mContext;
	private View mView;

	public String getMenuZnTitle() {
		return menuZnTitle;
	}

	public void setMenuZnTitle(String menuZnTitle) {
		this.menuZnTitle = menuZnTitle;
	}

	public String getMenuEnTitle() {
		return menuEnTitle;
	}

	public void setMenuEnTitle(String menuEnTitle) {
		this.menuEnTitle = menuEnTitle;
	}



	public NormalMenu(Context context, int menuId) {

		menuZnTitle = "";
		menuEnTitle = "";
		this.mMenuId = menuId;
		mContext = context;
	}

	public NormalMenu(Context context, int menuId, String znTitle,
			String enTitle) {
		menuZnTitle = znTitle;
		menuEnTitle = enTitle;
		this.mMenuId = menuId;
		mContext = context;
	}

	@Override
	public void add(ViewGroup viewGroup, final onMenuClickListener clickListener) {
		mView = LayoutInflater.from(mContext).inflate(R.layout.tab_layout,
				viewGroup, false);
		mView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				clickListener.onClick(mMenuId);

			}
		});

		TextView znTv = (TextView) mView.findViewById(R.id.tab_item_zh);
		znTv.setText(menuZnTitle);
		TextView enTv = (TextView) mView.findViewById(R.id.tab_item_en);
		enTv.setText(menuEnTitle);
		viewGroup.addView(mView);
	}

	@Override
	public void setMenuBackground(int resId) {
		mView.setBackgroundResource(resId);

	}

	@Override
	public void setMenuBackground(Drawable drawable) {
		mView.setBackground(drawable);
		
	}



}
