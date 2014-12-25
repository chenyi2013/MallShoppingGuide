package com.puji.mallshoppingguide.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.puji.event.EventBus;
import com.puji.mallshoppingguide.R;
import com.puji.mallshoppingguide.bean.Business;
import com.puji.mallshoppingguide.bean.Businesses;
import com.puji.mallshoppingguide.bean.RequestParams;
import com.puji.mallshoppingguide.cofig.Constants;
import com.puji.mallshoppingguide.utils.FormatUtils;

import java.util.ArrayList;
import java.util.HashMap;


public class FoodFragment extends Fragment {

    private static final String TYPE = "type";

    private DisplayImageOptions mOptions;
    private RecyclerView mRecyclerView;

    private FoodAdapter mAdapter;
    private ArrayList<Business> mData;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    public static FoodFragment newInstance(String type) {
        FoodFragment fragment = new FoodFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        String type = getArguments().getString(TYPE);
        HashMap<String, String> maps = new HashMap<String, String>();
        maps.put("Method", "business.getbusinessactivity");
        maps.put("ActivityType", type);
        EventBus.getDefault().post(
                new RequestParams(Method.POST, Constants.HOME_URL, FormatUtils
                        .formatParams(maps), Businesses.class));
        mOptions = new DisplayImageOptions.Builder()
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

    private void initUI() {

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycle_view);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void onEventMainThread(Businesses businesses) {

        if (businesses != null) {
            mData = businesses.getData();
            if (mData != null) {
                mAdapter = new FoodAdapter();
                mAdapter.bindData(mData);
                mRecyclerView.setAdapter(mAdapter);

            }

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

        ArrayList<Business> data = new ArrayList<Business>();

        public void bindData(ArrayList<Business> data) {
            if (data != null) {
                this.data = data;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.food_grid_item, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            Business business = data.get(position);
            viewHolder.businessName.setText(business.getBusinessName());
            viewHolder.businessNumber.setText(business.getBusinessName());
            viewHolder.businessTag.setText(business.getTag());
            viewHolder.info.setText(business.getInfo());
            ImageLoader.getInstance().displayImage(business.getLogoPath(), viewHolder.icon, mOptions);

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
                icon = (ImageView) itemView
                        .findViewById(R.id.icon);
                businessName = (TextView) itemView
                        .findViewById(R.id.business_name);
                businessNumber = (TextView) itemView
                        .findViewById(R.id.business_number);
                businessTag = (TextView) itemView
                        .findViewById(R.id.tag);
                info = (TextView) itemView
                        .findViewById(R.id.info);


            }

            ImageView icon;
            TextView businessName;
            TextView businessTag;
            TextView businessNumber;
            TextView info;
        }

    }


}
