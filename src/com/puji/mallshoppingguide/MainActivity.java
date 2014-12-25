package com.puji.mallshoppingguide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.puji.mallshoppingguide.fragment.ActivityInfoFragment;
import com.puji.mallshoppingguide.fragment.FloorGuideFragment;
import com.puji.mallshoppingguide.fragment.FoodFragment;
import com.puji.mallshoppingguide.fragment.HomeFragment;
import com.puji.mallshoppingguide.fragment.MemberFragment;
import com.puji.mallshoppingguide.fragment.StoreInfoFragment;
import com.puji.mallshoppingguide.menu.DistinctiveMenu;
import com.puji.mallshoppingguide.menu.Menuable;
import com.puji.mallshoppingguide.menu.Menuable.onMenuClickListener;
import com.puji.mallshoppingguide.menu.NormalMenu;
import com.puji.mallshoppingguide.service.DownloadDataService;

import java.util.ArrayList;

/**
 * @author Kevin
 */
public class MainActivity extends Activity implements onMenuClickListener {

    private LinearLayout mTabs;
    private ArrayList<Menuable> mMenus;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, DownloadDataService.class));
        initUI();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, DownloadDataService.class));
    }

    private void initUI() {
        initMenu();
        updateMenuBackground(3);
        initFragments();

    }

    private void initFragments() {
        mFragments = new ArrayList<Fragment>();
        mFragments.add(FloorGuideFragment.newInstance(""));
        mFragments.add(FoodFragment.newInstance("1"));
        mFragments.add(FoodFragment.newInstance("2"));
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(ActivityInfoFragment.newInstance(""));
        mFragments.add(MemberFragment.newInstance(""));
        mFragments.add(StoreInfoFragment.newInstance(""));
        getFragmentManager().beginTransaction()
                .add(R.id.content_layout, mFragments.get(3)).commit();
    }

    private void initMenu() {

        mTabs = (LinearLayout) findViewById(R.id.tabs_layout);
        mMenus = new ArrayList<Menuable>();
        mMenus.add(new NormalMenu(this, 0, getString(R.string.floor_guide_zh),
                getString(R.string.floor_guide_en)));
        mMenus.add(new NormalMenu(this, 1, getString(R.string.food_zh),
                getString(R.string.food_en)));
        mMenus.add(new NormalMenu(this, 2,
                getString(R.string.entertalnment_zh),
                getString(R.string.entertalnment_en)));
        mMenus.add(new DistinctiveMenu(this, 3));
        mMenus.add(new NormalMenu(this, 4,
                getString(R.string.activity_information_zh),
                getString(R.string.activity_information_en)));
        mMenus.add(new NormalMenu(this, 5, getString(R.string.member_zh),
                getString(R.string.member_en)));
        mMenus.add(new NormalMenu(this, 6, getString(R.string.store_info_zh),
                getString(R.string.store_info_en)));

        for (Menuable menu : mMenus) {
            menu.add(mTabs, this);
        }
    }

    public void updateMenuBackground(int menuId) {
        Menuable menu = null;
        for (int i = 0; i < mMenus.size(); i++) {
            menu = mMenus.get(i);
            if (menuId == menu.getmMenuId()) {

                if (menu instanceof NormalMenu) {
                    menu.setMenuBackground(R.drawable.top_tab_bg);
                } else {
                    menu.setMenuBackground(R.drawable.home_true);
                }
            } else {
                if (menu instanceof NormalMenu) {
                    menu.setMenuBackground(R.drawable.top_tab_bg_false);
                } else {
                    menu.setMenuBackground(R.drawable.home_false);
                }
            }
        }
    }

    @Override
    public void onClick(int menuId) {
        updateMenuBackground(menuId);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_layout, mFragments.get(menuId)).commit();
    }

}
