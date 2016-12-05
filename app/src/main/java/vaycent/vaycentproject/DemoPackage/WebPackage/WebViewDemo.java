package vaycent.vaycentproject.DemoPackage.WebPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import vaycent.vaycentproject.R;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

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
    }

    private void InitMyWebView(){
        myWebView.getSettings().setJavaScriptEnabled(true);//支持JavaScript
        myWebView.getSettings().setUseWideViewPort(true);//设置载入页面自适应手机屏幕，居中显示
        myWebView.getSettings().setLoadWithOverviewMode(true);//设置载入页面自适应手机屏幕，居中显示
        myWebView.getSettings().setBuiltInZoomControls(true);//支持缩放
        myWebView.getSettings().setCacheMode(LOAD_NO_CACHE);//设置缓存策略

        myWebView.loadUrl("https://www.baidu.com/");
    }
}
