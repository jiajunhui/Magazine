package com.kk.taurus.app.magazine.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UIWebView extends WebView {
	private final String TAG = "UIWebView";

	private OnLoadProgressListener onLoadProgressListener;

	public UIWebView(Context context) {
		super(context);
	}

	public UIWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public UIWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onSizeChanged(int w, int h, int ow, int oh) {
		super.onSizeChanged(w, h, ow, oh);
		Log.d(TAG,"onSizeChanged : w = " + w + " h = " + h + " ow = " + ow + " oh = " + oh);
	}

	/**
	 * 默认设置：支持JS、不能缩放、自适应屏幕、启用DOM存储、背景透明、屏蔽硬件加速、水平不滑动、垂直不滑动
	 * 
	 */
	@SuppressLint("NewApi")
	public void setDefaultSettings() {
		WebSettings webSettings = this.getSettings();
		webSettings.setJavaScriptEnabled(true); // 支持js
		webSettings.setBuiltInZoomControls(false);// 设置支持缩放..
		webSettings.setSupportZoom(false);// 是否支持缩放
		webSettings.setUseWideViewPort(false);//
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setDomStorageEnabled(true);
		// 开启 DOM storage API 功能
		webSettings.setDomStorageEnabled(true);
		// 开启 database storage API 功能
		webSettings.setDatabaseEnabled(true);
		webSettings
				.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		this.setBackgroundColor(0x00000000);
//		this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//		webSettings.setAllowFileAccess(true);

		this.setHorizontalScrollBarEnabled(false);
		this.setVerticalScrollBarEnabled(false);
		// 延迟加载图片
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			webSettings.setLoadsImagesAutomatically(true);
			if ((Build.VERSION.RELEASE.contains("4.4.2")&&
					(Build.MODEL.contains("vivo Xplay3S")
					|| Build.MODEL.contains("Coolpad 8675")
					)) || Build.MODEL.contains("NX505J")) {
				// 再4.4.2系统，图片过多时候使用webView，卡死
				this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			}
		} else {
			webSettings.setLoadsImagesAutomatically(false);
		}
		this.setWebViewClient(webViewClient);

		setWebChromeClient(new WebChromeClient());
	}

	private class WebChromeClient extends android.webkit.WebChromeClient{
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			if(onLoadProgressListener!=null){
				onLoadProgressListener.onProgressChanged(view, newProgress);
			}
		}
	}

	WebViewClient webViewClient = new WebViewClient() {

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			// TODO 远程页面出现404的时候
			// view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
			// mErrorFrame.setVisibility(View.VISIBLE);
		}

		@Override
		public void onPageFinished(final WebView view, String url) {
			super.onPageFinished(view, url);
			if (!view.getSettings().getLoadsImagesAutomatically()) {
				view.getSettings().setLoadsImagesAutomatically(true);
			}
		}

	};

	public void setOnLoadProgressListener(OnLoadProgressListener onLoadProgressListener) {
		this.onLoadProgressListener = onLoadProgressListener;
	}

	public interface OnLoadProgressListener {
		void onProgressChanged(WebView webView, int newProgress);
	}


}
