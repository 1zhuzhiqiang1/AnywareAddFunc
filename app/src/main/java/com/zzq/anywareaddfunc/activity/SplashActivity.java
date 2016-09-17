package com.zzq.anywareaddfunc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zzq.anywareaddfunc.utils.ImageLoaderUtils;
import com.zzq.anywareaddfunc.R;
import com.zzq.anywareaddfunc.utils.SPUtils;

/**
 * Created by zhuzhiqiang on 2016/8/15 0015.
 */
public class SplashActivity extends Activity implements OnClickListener {

    private ImageView iv_splash = null;
    private String imageUrl = "http://p1.so.qhmsg.com/bdr/326__/t01b99d7be1e233f976.jpg";
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        initView();
        initEvent();
        initData();
    }

    private void initEvent() {
        iv_splash.setOnClickListener(this);
    }

    private void initData() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                ImageLoaderUtils.getInstance(context).display(imageUrl, iv_splash);
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                delayJump();
            }
        }.execute();
    }

    private void initView() {
        iv_splash = (ImageView) findViewById(R.id.iv_splash);
    }

    /**
     * 延迟跳转
     */
    private void delayJump() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!((Boolean) SPUtils.get(context, SPUtils.SHOW_GUIDE, false))) {
                    startActivity(new Intent(context, GuideActivity.class));
                } else if (!((Boolean) SPUtils.get(context, SPUtils.LOGIN, false))) {
                    startActivity(new Intent(context, LoginActiviy.class));
                } else {
                    startActivity(new Intent(context, MainActivity.class));
                }
                finish();
            }
        }, 2000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onDestroy() {
        ImageLoader imageLoader = ImageLoaderUtils.getInstance(context).getImageLoader();
        if (imageLoader != null) {
            imageLoader.clearMemoryCache();
        }

        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_splash:
                clickSplash();
                break;
        }
    }

    /**
     * 使用系统浏览器打开网址
     */
    private void clickSplash() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com/"));
        startActivity(intent);
    }
}
