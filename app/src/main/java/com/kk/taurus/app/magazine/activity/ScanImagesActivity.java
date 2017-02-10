package com.kk.taurus.app.magazine.activity;

import android.os.Bundle;

import com.kk.taurus.app.magazine.bean.ImagesData;
import com.kk.taurus.app.magazine.holder.ScanImagesHolder;
import com.kk.taurus.baseframe.ui.activity.StateActivity;

/**
 * Created by Taurus on 2017/2/10.
 */

public class ScanImagesActivity extends StateActivity<ImagesData,ScanImagesHolder> {

    public static final String KEY_IMAGES = "images";

    @Override
    public ScanImagesHolder getContentViewHolder(Bundle savedInstanceState) {
        return new ScanImagesHolder(this);
    }

    @Override
    public void initData() {
        super.initData();
        fullScreen();
        ImagesData imagesData = (ImagesData) getIntent().getSerializableExtra(KEY_IMAGES);
        setData(imagesData);
    }
}
