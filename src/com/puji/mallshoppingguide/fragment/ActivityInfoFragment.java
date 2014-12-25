package com.puji.mallshoppingguide.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.etsy.android.grid.StaggeredGridView;
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

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link ActivityInfoFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link ActivityInfoFragment#newInstance} factory method to create an instance
 * of this fragment.
 */
public class ActivityInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DisplayImageOptions mOptions;
    private InfoAdapter mAdapter;

    private StaggeredGridView mStaggeredGridView;
    private ArrayList<Business> mData;


    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ActivityInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityInfoFragment newInstance(String param1) {
        ActivityInfoFragment fragment = new ActivityInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ActivityInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        EventBus.getDefault().register(this);

        // String type = getArguments().getString(TYPE);
        HashMap<String, String> maps = new HashMap<String, String>();
        maps.put("Method", "business.getbusinessactivity");
        maps.put("ActivityType", "3");
        EventBus.getDefault().post(
                new RequestParams(Request.Method.POST, Constants.HOME_URL, FormatUtils
                        .formatParams(maps), Businesses.class));
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    public void onEventMainThread(Businesses businesses) {

        if (businesses != null) {
            mData = businesses.getData();
            if (mData != null) {
                mAdapter = new InfoAdapter();
                mAdapter.bindData(mData);
                mStaggeredGridView.setAdapter(mAdapter);

            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_info, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }


    private void initUI() {
        mStaggeredGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
    }

    class InfoAdapter extends BaseAdapter {

        ArrayList<Business> data = new ArrayList<Business>();

        public void bindData(ArrayList<Business> data) {
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
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.food_grid_item, parent, false);
                viewHolder.icon = (ImageView) convertView
                        .findViewById(R.id.icon);
                viewHolder.businessName = (TextView) convertView
                        .findViewById(R.id.business_name);
                viewHolder.businessNumber = (TextView) convertView
                        .findViewById(R.id.business_number);
                viewHolder.businessTag = (TextView) convertView
                        .findViewById(R.id.tag);
                viewHolder.info = (TextView) convertView
                        .findViewById(R.id.info);

                convertView.setTag(viewHolder);
            }

            viewHolder = (ViewHolder) convertView.getTag();
            Business business = data.get(position);
            viewHolder.businessName.setText(business.getBusinessName());
            viewHolder.businessNumber.setText(business.getBusinessName());
            viewHolder.businessTag.setText(business.getTag());
            viewHolder.info.setText(business.getInfo());
            ImageLoader.getInstance().displayImage(business.getLogoPath(), viewHolder.icon, mOptions);
            return convertView;
        }

        class ViewHolder {
            ImageView icon;
            TextView businessName;
            TextView businessTag;
            TextView businessNumber;
            TextView info;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
