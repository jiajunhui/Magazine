package com.kk.taurus.app.magazine.activity;

import android.os.Bundle;

import com.kk.taurus.app.magazine.bean.WebPageData;
import com.kk.taurus.app.magazine.holder.WebViewHolder;
import com.kk.taurus.baseframe.ui.activity.TopBarActivity;

/**
 * Created by Taurus on 2017/2/7.
 */

public class WebViewActivity extends TopBarActivity<WebPageData,WebViewHolder> {

    public static final String KEY_WEB_PAGE_DATA = "web_page_data";

    @Override
    public WebViewHolder getContentViewHolder(Bundle savedInstanceState) {
        return new WebViewHolder(this);
    }

    @Override
    public void initData() {
        super.initData();
        setStatusBarColor(getResources().getColor(com.kk.taurus.baseframe.R.color.top_bar_background));
    }

    @Override
    public void parseIntent() {
        super.parseIntent();
        WebPageData webPageData = (WebPageData) getIntent().getSerializableExtra(KEY_WEB_PAGE_DATA);
        setTopBarTitle(webPageData.getTitle());
        setData(webPageData);
    }
}
