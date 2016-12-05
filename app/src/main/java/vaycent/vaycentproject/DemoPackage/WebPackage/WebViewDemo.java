package vaycent.vaycentproject.DemoPackage.WebPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import vaycent.vaycentproject.R;

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
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);

        myWebView.loadUrl("https://www.baidu.com/");
    }
}
