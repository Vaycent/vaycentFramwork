package vaycent.vaycentproject.DemoPackage.WebPackage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.BuildConfig;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

import static android.webkit.WebSettings.LOAD_DEFAULT;

/**
 * Created by Vaycent on 2016/12/5.
 */

public class WebViewDemo extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_demo_layout);

        myWebView = (WebView)findViewById(R.id.id_webview);

        InitMyWebView();

        WebViewClientControl();

        WebChromeClientControl();

        //开通webview的debu模式
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        resumeWebView();
    }

    @Override
    protected void onPause(){
        super.onPause();
        pauseWebView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        destroyWebView();
    }

    @Override
    public void onBackPressed(){
        if(myWebView.canGoBack()){
//            myWebView.goBack();
            myWebView.loadUrl("javascript:window.history.back();");//这种back的方式可以避免重定向
        }else{
            super.onBackPressed();
        }
    }



    private void InitMyWebView(){
        myWebView.getSettings().setJavaScriptEnabled(true);//支持JavaScript
        myWebView.getSettings().setUseWideViewPort(true);//设置载入页面自适应手机屏幕，居中显示
        myWebView.getSettings().setLoadWithOverviewMode(true);//设置载入页面自适应手机屏幕，居中显示
        myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);//支持media播放
        myWebView.getSettings().setBuiltInZoomControls(true);//支持缩放
        myWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//BASE64 图片显示

        // 设置缓存策略 LOAD_DEFAULT, LOAD_CACHE_ELSE_NETWORK, LOAD_NO_CACHE, LOAD_CACHE_ONLY
        myWebView.getSettings().setCacheMode(LOAD_DEFAULT);

        myWebView.loadUrl("https://www.healthreach.hk/ehc/tablet/login.jsp?v=1&uid=04:EC:CE:2A:2C:26:80");
    }

    private void WebViewClientControl(){
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String link) {
                mlog.v("Catch this link:"+link);
                myWebView.loadUrl(link);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap bitmap) {
                super.onPageStarted(view, url, bitmap);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                           SslError error){
                super.onReceivedSslError(view,handler,error);
            }
        });
    }

    private void WebChromeClientControl(){
        myWebView.setWebChromeClient(new WebChromeClient()
        {
            // For Lollipop 5.0+ Devices
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
            {
                mlog.e("onShowFileChooser here");
                return true;
            }
        });
    }

    //**************** Control The WebView Thread ****************
    private void resumeWebView() {
        if (myWebView == null) {
            return;
        }
        myWebView.onResume();
    }

    private void pauseWebView() {
        if (myWebView == null) {
            return;
        }
        myWebView.onPause();
    }

    private void destroyWebView() {
        if (myWebView != null) {
            myWebView.removeAllViews();
            myWebView.destroy();
            myWebView = null;
        }
    }





}
