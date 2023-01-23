package com.mstech.gamesnatcherz.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mstech.gamesnatcherz.R;


/** HARISH GADDAM */

public class WebViewWithNavigation extends Activity {

    private WebView mWebView;
    private ImageView ivBack;
    private Button btnBackword;
    private Button btnForward;
    private ProgressBar mProgressBar;
    TextView tvHeader;

    String url, originalUrl, title;

    private void showNavigationItemInfo(WebHistoryItem navigationItem){
        url = navigationItem.getUrl();
        originalUrl = navigationItem.getOriginalUrl();
        title = navigationItem.getTitle();
        tvHeader.setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_webview_navigation);
        mWebView = (WebView) findViewById(R.id.mWebView);
        ivBack = findViewById(R.id.ivBack);
        tvHeader = findViewById(R.id.tvHeader);
        mProgressBar = findViewById(R.id.mProgressBar);

        ivBack.setOnClickListener(view -> onBackPressed());
        tvHeader.setText(getIntent().getStringExtra("type"));

        btnBackword = findViewById(R.id.btnBackword);
        btnForward = findViewById(R.id.btnForward);

        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.getUseWideViewPort();
        webSettings.setDatabaseEnabled(true);
        webSettings.setSaveFormData(true);
        webSettings.setSavePassword(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        btnBackword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go backward
                if (mWebView != null &&
                        mWebView.canGoBack()) {
                    mWebView.goBack();
                }
                WebHistoryItem navigationItem = mWebView.copyBackForwardList().getCurrentItem();
                showNavigationItemInfo(navigationItem);
            }
        });

        btnForward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go forward
                if (mWebView != null &&
                        mWebView.canGoForward()) {
                    mWebView.goForward();
                }
                WebHistoryItem navigationItem = mWebView.copyBackForwardList().getCurrentItem();
                showNavigationItemInfo(navigationItem);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });

        startWebView(getIntent().getStringExtra("url"));
//        mWebView.loadUrl(getIntent().getStringExtra("url"), null);
    }

    private void startWebView(String weblink) {
        mWebView.setWebViewClient(new WebViewClient() {

            //If you will not use this method url links are open in new browser not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                mProgressBar.setVisibility(View.VISIBLE);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
            }
        });

        // Javascript inabled on webview
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.clearHistory();
        mWebView.clearFormData();
        mWebView.clearCache(true);

        mWebView.loadUrl(weblink);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}