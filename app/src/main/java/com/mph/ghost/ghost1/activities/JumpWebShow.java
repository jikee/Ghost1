package com.mph.ghost.ghost1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.mph.ghost.ghost1.R;
import com.mph.ghost.ghost1.utils.AllActivitiesHolder;
import com.mph.ghost.ghost1.widget.TitleBar;

import butterknife.BindView;

/**
 * @author 马鹏昊
 * @date {date}
 * @des
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class JumpWebShow extends BaseActivity {

    @BindView(R.id.title)
    TitleBar mTitle;
    @BindView(R.id.web)
    BridgeWebView mWeb;
    @BindView(R.id.nullPageTip)
    LinearLayout mNullPageTip;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner_web_show;
    }

    private void init() {

        mTitle.setRightButtonEnable(false);
        mTitle.setEvents(new TitleBar.AddClickEvents() {
            @Override
            public void clickLeftButton() {
                AllActivitiesHolder.removeAct(JumpWebShow.this);
            }

            @Override
            public void clickRightButton() {
                //分享
            }
        });

        mWeb.getSettings().setJavaScriptEnabled(true);
        // 获取到UserAgentString 并修改
        String userAgent = mWeb.getSettings().getUserAgentString();
        mWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持js调用window.open方法
        mWeb.getSettings().setJavaScriptEnabled(true);// 设置支持javascript
        mWeb.getSettings().setUserAgentString(userAgent + " cloudcns/1.0");
        mWeb.getSettings().setSupportMultipleWindows(true);
        mWeb.getSettings().setSupportZoom(true);
        mWeb.getSettings().setBuiltInZoomControls(true);
        mWeb.setWebViewClient(new BridgeWebViewClient(mWeb));

        Intent intent = getIntent();
        if (intent != null) {

            boolean ifHasTitle = intent.getBooleanExtra("ifHasTitle", true);
            if (ifHasTitle) {
                mTitle.setVisibility(View.VISIBLE);
                String title = intent.getStringExtra("title");
                mTitle.setTitle(title);
            } else {
                mTitle.setVisibility(View.GONE);
            }

            mUrl = intent.getStringExtra("linkUrl");
            if (TextUtils.isEmpty(mUrl)) {
                mNullPageTip.setVisibility(View.VISIBLE);
                mWeb.setVisibility(View.GONE);
                if (mTitle.getVisibility() == View.VISIBLE)
                    mTitle.setTitle("找不到页面");
            } else {
                mNullPageTip.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.loadUrl(mUrl);
            }
        } else {
            mNullPageTip.setVisibility(View.VISIBLE);
            mWeb.setVisibility(View.GONE);
            return;
        }

    }

}
