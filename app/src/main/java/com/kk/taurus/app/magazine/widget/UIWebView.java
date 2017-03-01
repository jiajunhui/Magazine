package com.kk.taurus.app.magazine.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIWebView extends WebView {
	private final String TAG = "UIWebView";

	// 获取img标签正则
	private static final String IMAGE_URL_TAG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	private static final String IMAGE_URL_CONTENT = "http:\"?(.*?)(\"|>|\\s+)";

	private List<String> mImages = new ArrayList<>();

	private OnLoadProgressListener onLoadProgressListener;

	private OnImageListener onImageListener;

	public UIWebView(Context context) {
		this(context,null);
	}

	public UIWebView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public UIWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	protected void init(Context context) {
		addJavascriptInterface(new ImageHandler(),"imageHandler");
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
			Log.d(TAG,"url : " + url);
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
			mImages = new ArrayList<>();
			setImageClickListner();
			parseHTML();
		}

	};

	/**
	 * 注入 js 函数监听，这段 js 函数的功能就是，遍历所有的图片，并添加 onclick 函数，实现点击事件，
	 * 函数的功能是在图片点击的时候调用本地java接口并传递 url 过去
	 */
	private void setImageClickListner() {
		// 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
		this.loadUrl("javascript:(function(){" +
				"var objs = document.getElementsByTagName(\"img\"); " +
				"for(var i=0;i<objs.length;i++)  " +
				"{"
				+ "    objs[i].onclick=function()  " +
				"    {  "
				+ "        window.imageHandler.onClickImage(this.src);  " +
				"    }  " +
				"}" +
				"})()");
	}

	/**
	 * 解析 HTML 该方法在 setWebViewClient 的 onPageFinished 方法中进行调用
	 */
	private void parseHTML() {
		loadUrl("javascript:window.imageHandler.parseHtml('<head>'+"
				+ "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
	}

	public void setOnLoadProgressListener(OnLoadProgressListener onLoadProgressListener) {
		this.onLoadProgressListener = onLoadProgressListener;
	}

	public void setOnImageListener(OnImageListener onImageListener) {
		this.onImageListener = onImageListener;
	}

	public interface OnLoadProgressListener {
		void onProgressChanged(WebView webView, int newProgress);
	}

	public interface OnImageListener{
		void onImageParsed(List<String> images);
		void onImageClick(List<String> images,String url);
	}

	private class ImageHandler{

		@JavascriptInterface
		public void parseHtml(String html){
			List<String> images = new ArrayList<>();
			Matcher matcher = Pattern.compile(IMAGE_URL_TAG).matcher(html);
			while (matcher.find()){
				images.add(matcher.group());
			}
			for (String image : images) {
				Matcher matcherSrc = Pattern.compile(IMAGE_URL_CONTENT).matcher(image);
				while (matcherSrc.find()) {
					String img = matcherSrc.group().substring(0, matcherSrc.group().length() - 1);
					Log.d(TAG,img);
					mImages.add(img);
				}
			}
			if(onImageListener!=null){
				onImageListener.onImageParsed(mImages);
			}
		}

		private void listDiff(List<String> images){
			if(images!=null){

			}
		}

		@JavascriptInterface
		public void onClickImage(String url){
			if(onImageListener!=null){
				onImageListener.onImageClick(mImages,url);
			}
		}

	}


}
