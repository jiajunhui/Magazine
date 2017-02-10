package com.kk.taurus.app.magazine.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kk.taurus.app.magazine.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/2/10.
 */

public class ScanImagesAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImages = new ArrayList<>();

    public ScanImagesAdapter(Context context, List<String> images){
        this.mContext = context;
        this.mImages = images;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Glide.with(mContext)
                .load(mImages.get(position))
                .placeholder(R.mipmap.ic_default_img)
                .crossFade()
                .into(imageView);

        container.addView(imageView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
