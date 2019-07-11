package com.auna.alc4phase1_umarsaiduauna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class About_ALC extends AppCompatActivity {
    ProgressBar loadingProgressBar;
    private WebView mwebView;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__alc);
        mwebView =findViewById(R.id.webView_);
        loadingProgressBar = findViewById(R.id.progressbar_);
        mySwipeRefreshLayout = findViewById(R.id.swipeContainer);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        layout = findViewById(R.id.layout);

        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        mwebView.getSettings().setAppCachePath(getCacheDir().getAbsolutePath());
        mwebView.getSettings().setAllowFileAccess(true);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setFocusableInTouchMode(true);
        mwebView.setScrollbarFadingEnabled(true);
        mwebView.getSettings().setLoadsImagesAutomatically(true);
        mwebView.getSettings().setDomStorageEnabled(true);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setDatabaseEnabled(true);
        mwebView.getSettings().setUseWideViewPort(true);
        mwebView.getSettings().setSavePassword(true);
        mwebView.getSettings().setSaveFormData(true);

        if (isNetworkConnected() || isWifiConnected()) {
            mwebView.loadUrl("https://andela.com/alc/");


        } else {

            loadingProgressBar.setVisibility(View.GONE);
            Snackbar snackbar = Snackbar.make(layout, "Try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mwebView.loadUrl("https://andela.com/alc/");

                }
            });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (isNetworkConnected() || isWifiConnected()) {
                            mwebView.loadUrl("https://andela.com/alc/");

                        }else{
                            Snackbar snackbar = Snackbar.make(layout, "Try again!", Snackbar.LENGTH_LONG).setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mwebView.loadUrl("https://andela.com/alc/");
                                }
                            });
                            snackbar.setActionTextColor(Color.RED);
                            View sbView = snackbar.getView();
                            TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                            textView.setTextColor(Color.YELLOW);
                            snackbar.show();
                        }
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        mwebView.setWebChromeClient(new WebChromeClient());

        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mwebView.setWebChromeClient(new WebChromeClient(){


            public void onProgressChanged(WebView view, int newProgress){
                super.onProgressChanged(view,newProgress);
                loadingProgressBar.setProgress(newProgress);
                if(newProgress == 100) {
                    loadingProgressBar.setVisibility(View.GONE);
                }else{
                    loadingProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        mwebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mwebView.canGoBack()) {
                    mwebView.goBack();
                    return true;
                }
                return false;
            }
        });



    }

    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) && networkInfo.isConnected();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
