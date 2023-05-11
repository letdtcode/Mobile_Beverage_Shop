package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.model.Banner;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Banner> bannerList;

    public BannerAdapter(Context mContext, List<Banner> bannerList) {
        this.mContext = mContext;
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner_home, container, false);
        ImageView imgBanner = view.findViewById(R.id.imgBanner);
        Banner banner = bannerList.get(position);
        if (banner != null) {
            Glide.with(mContext).load(banner.getResourceId()).into(imgBanner);
        }
        //        Add view to viewgroup
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (bannerList != null) {
            return bannerList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //        Remove view
        container.removeView((View) object);
    }
}
