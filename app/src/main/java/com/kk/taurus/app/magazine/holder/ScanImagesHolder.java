package com.kk.taurus.app.magazine.holder;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.kk.taurus.app.magazine.R;
import com.kk.taurus.app.magazine.adapter.ScanImagesAdapter;
import com.kk.taurus.app.magazine.bean.ImagesData;
import com.kk.taurus.baseframe.base.ContentHolder;

import java.util.List;

/**
 * Created by Taurus on 2017/2/10.
 */

public class ScanImagesHolder extends ContentHolder<ImagesData> {

    private ViewPager mViewPager;
    private TextView mTvState;

    public ScanImagesHolder(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_scan_images);
        mViewPager = getViewById(R.id.viewPager);
        mTvState = getViewById(R.id.tv_state);
    }

    @Override
    public void refreshView() {
        super.refreshView();
        final List<String> images = mData.getImages();
        int currPos = images.indexOf(mData.getCurrUrl());
        mViewPager.setAdapter(new ScanImagesAdapter(mContext,mData.getImages()));
        mViewPager.setCurrentItem(currPos);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvState.setText((position + 1) + " / " + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
