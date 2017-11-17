package com.mph.ghost.ghost1.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.data.GlobalData;
import com.mph.ghost.ghost1.utils.DoubleClickExitUtil;

import butterknife.BindView;

public class WebMainActivity extends BaseActivity {

    @BindView(R.id.wv_webmain_showurl)
    WebView mWvWebmainShowurl;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = ProgressDialog.show(this,"","加载中..");
        mWvWebmainShowurl.getSettings().setJavaScriptEnabled(true);
        // 获取到UserAgentString 并修改
        String userAgent = mWvWebmainShowurl.getSettings().getUserAgentString();
        mWvWebmainShowurl.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持js调用window.open方法
        mWvWebmainShowurl.getSettings().setJavaScriptEnabled(true);// 设置支持javascript
        mWvWebmainShowurl.getSettings().setUserAgentString(userAgent + " cloudcns/1.0");
        mWvWebmainShowurl.getSettings().setSupportMultipleWindows(true);
        mWvWebmainShowurl.getSettings().setSupportZoom(true);
        mWvWebmainShowurl.getSettings().setBuiltInZoomControls(true);
        mWvWebmainShowurl.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressDialog.dismiss();
                super.onPageFinished(view, url);
            }
        });

        mWvWebmainShowurl.loadUrl(GlobalData.url);
    }

    @Override
    public void onBackPressed() {
        DoubleClickExitUtil.tryExit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_main;
    }
}
