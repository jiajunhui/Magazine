package com.kk.taurus.app.magazine.activity;

import android.content.Intent;
import android.os.Bundle;

import com.kk.taurus.app.magazine.bean.ImagesData;
import com.kk.taurus.app.magazine.bean.WebPageData;
import com.kk.taurus.app.magazine.holder.WebViewHolder;
import com.kk.taurus.baseframe.ui.activity.ToolBarActivity;

import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class WebViewActivity extends ToolBarActivity<WebPageData,WebViewHolder> implements WebViewHolder.OnWebViewHolderListener {

    public static final String KEY_WEB_PAGE_DATA = "web_page_data";

    @Override
    public WebViewHolder getContentViewHolder(Bundle savedInstanceState) {
        return new WebViewHolder(this,this);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void parseIntent() {
        super.parseIntent();
        WebPageData webPageData = (WebPageData) getIntent().getSerializableExtra(KEY_WEB_PAGE_DATA);
        setToolBarTitle(webPageData.getTitle());
        setData(webPageData);
    }

    @Override
    public void onImageClick(List<String> images, String url) {
        ImagesData imagesData = new ImagesData();
        imagesData.setCurrUrl(url);
        imagesData.setImages(images);
        Intent intent = new Intent(this,ScanImagesActivity.class);
        intent.putExtra(ScanImagesActivity.KEY_IMAGES,imagesData);
        startActivity(intent);
    }
}
