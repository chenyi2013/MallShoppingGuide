package com.puji.mallshoppingguide.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.puji.mallshoppingguide.R;

public class DistinctiveMenu extends Menuable {

	private Context mContext;
	private ImageView mView;

	public DistinctiveMenu(Context context, int menuId) {
		mContext = context;
		mMenuId = menuId;
	}

	@Override
	public void add(ViewGroup viewGroup, final onMenuClickListener clickListener) {

		mView = (ImageView)LayoutInflater.from(mContext).inflate(
				R.layout.img_menu_layout, viewGroup, false);
		mView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				clickListener.onClick(mMenuId);

			}
		});
		viewGroup.addView(mView);

	}

	@Override
	public void setMenuBackground(int resId) {
		mView.setImageResource(resId);

	}

	@Override
	public void setMenuBackground(Drawable drawable) {

		mView.setImageDrawable(drawable);
	}
}
