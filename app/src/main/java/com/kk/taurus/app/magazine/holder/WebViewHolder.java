package com.kk.taurus.app.magazine.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.kk.taurus.app.magazine.R;
import com.kk.taurus.app.magazine.bean.WebPageData;
import com.kk.taurus.app.magazine.widget.UIWebView;
import com.kk.taurus.baseframe.base.ContentHolder;

/**
 * Created by Taurus on 2017/2/7.
 */

public class WebViewHolder extends ContentHolder<WebPageData> implements UIWebView.OnLoadProgressListener {

    private ProgressBar mProgressBar;
    private RelativeLayout mContainer;
    private UIWebView mWebView;

    public WebViewHolder(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_webview);
        mProgressBar = getViewById(R.id.progressBar);
        mContainer = getViewById(R.id.webView_container);
        mWebView = new UIWebView(mContext);
        mWebView.setOnLoadProgressListener(this);
        mWebView.setDefaultSettings();
        mContainer.addView(mWebView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void refreshView() {
        super.refreshView();
        loadUrl(mData.getUrl());
    }

    private void loadUrl(String url){
        mWebView.loadUrl(url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mWebView!=null){
            mWebView.destroy();
        }
        if(mContainer!=null){
            mContainer.removeAllViews();
        }
    }

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        if(mProgressBar!=null){
            mProgressBar.setProgress(newProgress);
            if(newProgress>=100){
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }
}
