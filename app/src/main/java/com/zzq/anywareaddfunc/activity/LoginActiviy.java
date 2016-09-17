package com.zzq.anywareaddfunc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzq.anywareaddfunc.R;

/**
 * Created by zhuzhiqiang on 2016/8/16 0016.
 */
public class LoginActiviy extends Activity implements View.OnClickListener {

    private TextView tv_register = null;
    private TextView tv_forget_password = null;
    private TextView tv_login = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();

    }

    private void initEvent() {
        tv_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    private void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_login = (TextView) findViewById(R.id.tv_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                register();
                break;
            case R.id.tv_login:
                login();
                break;
        }
    }

    /*登录操作*/
    private void login() {
        /*在这里做登陆操作*/

        startActivity(new Intent(this, MainActivity.class));
    }

    /*注册操作*/
    private void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
