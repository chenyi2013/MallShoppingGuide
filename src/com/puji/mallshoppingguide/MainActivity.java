package com.puji.mallshoppingguide;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.puji.mallshoppingguide.bean.DistinctiveMenu;
import com.puji.mallshoppingguide.bean.Menuable;
import com.puji.mallshoppingguide.bean.Menuable.onMenuClickListener;
import com.puji.mallshoppingguide.bean.NormalMenu;

public class MainActivity extends Activity implements onMenuClickListener {

	private LinearLayout mTabs;
	private ArrayList<Menuable> mMenus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		mTabs = (LinearLayout) findViewById(R.id.tabs_layout);
		initMenu(mTabs);
		updateMenuBackground(4);
	}

	private void initMenu(ViewGroup vewGroup) {

		mMenus = new ArrayList<Menuable>();
		mMenus.add(new NormalMenu(this, 1, "楼层指引", "Floor guide"));
		mMenus.add(new NormalMenu(this, 2, "餐饮美食", "Food"));
		mMenus.add(new NormalMenu(this, 3, "休闲娱乐", "Entertalnment"));
		mMenus.add(new DistinctiveMenu(this, 4));
		mMenus.add(new NormalMenu(this, 5, "活动信息", "Activity information"));
		mMenus.add(new NormalMenu(this, 6, "VIVA会", "Member"));
		mMenus.add(new NormalMenu(this, 7, "模糊查找", "Store Information"));

		for (Menuable menu : mMenus) {
			menu.add(mTabs, this);
		}
	}
	
	
	public void updateMenuBackground(int menuId){
		Menuable menu = null;
		for(int i = 0; i<mMenus.size();i++ ){
			menu = mMenus.get(i);
			if(menuId == menu.getmMenuId()){
				
				if(menu instanceof NormalMenu){
					menu.setMenuBackground(R.drawable.top_tab_bg);
				}else{
					menu.setMenuBackground(R.drawable.home_true);
				}
			}else {
				if(menu instanceof NormalMenu){
					menu.setMenuBackground(R.drawable.top_tab_bg_false);
				}else{
					menu.setMenuBackground(R.drawable.home_false);
				}
			}
		}
	}

	@Override
	public void onClick(int menuId) {
		updateMenuBackground(menuId);
		switch (menuId) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;

		default:
			break;
		}
	}

}
