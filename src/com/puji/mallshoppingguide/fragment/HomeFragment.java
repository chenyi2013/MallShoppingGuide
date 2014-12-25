package com.puji.mallshoppingguide.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.puji.event.EventBus;
import com.puji.mallshoppingguide.R;
import com.puji.mallshoppingguide.bean.Category;
import com.puji.mallshoppingguide.bean.Categorys;
import com.puji.mallshoppingguide.bean.RequestParams;
import com.puji.mallshoppingguide.cofig.Constants;
import com.puji.mallshoppingguide.utils.FormatUtils;

public class HomeFragment extends Fragment {

	private GridView mGridView;
	private ArrayList<Category> mData;

	private DisplayImageOptions options;
	private HomeAdapter mAdapter;

	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();

		fragment.setArguments(args);
		return fragment;
	}

	public HomeFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		EventBus.getDefault().register(this);
		HashMap<String, String> params = new HashMap<>();
		params.put(Constants.HOME_KEY, Constants.HOME_VALUE);
		EventBus.getDefault().post(
				new RequestParams(Method.POST, Constants.HOME_URL, FormatUtils
						.formatParams(params), Categorys.class));

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initUI();

	}

	public void onEventMainThread(Categorys categorys) {
		mData = categorys.getData();
		if (mData != null) {
			mAdapter = new HomeAdapter();
			mAdapter.bindData(mData);
			mGridView.setAdapter(mAdapter);
		}

	}

	private void initUI() {

		mGridView = (GridView) getView().findViewById(R.id.home_grid_view);

	}

	class HomeAdapter extends BaseAdapter {

		private ArrayList<Category> data = new ArrayList<>();

		class ViewHolder {
			TextView titleZh;
			TextView titleEn;
			ImageView icon;
		}

		public void bindData(ArrayList<Category> data) {

			if (data != null) {
				this.data = data;
			}
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.home_grid_view_item, parent, false);

				viewHolder = new ViewHolder();
				viewHolder.icon = (ImageView) convertView
						.findViewById(R.id.icon);
				viewHolder.titleEn = (TextView) convertView
						.findViewById(R.id.title_en);
				viewHolder.titleZh = (TextView) convertView
						.findViewById(R.id.title_zh);

				convertView.setTag(viewHolder);
			}

			Category category = data.get(position);
			viewHolder = (ViewHolder) convertView.getTag();

			viewHolder.titleZh.setText(category.getName());
			viewHolder.titleEn.setText(category.getEnglishName());
			ImageLoader.getInstance().displayImage(category.getIcon(),
					viewHolder.icon, options);
			return convertView;
		}

	}

}
