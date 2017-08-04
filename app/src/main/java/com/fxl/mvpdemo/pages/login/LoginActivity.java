package com.fxl.mvpdemo.pages.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.fxl.mvpdemo.R;
import com.fxl.mvpdemo.mvp.base.BaseActivity;
import com.fxl.mvpdemo.pages.home.HomeActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginCountact.LoginView {

    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    private ObservableEmitter<Integer> clickEmitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //创建过滤抖动
        Observable.create((ObservableOnSubscribe<Integer>) e -> clickEmitter = e)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(integer -> doOnClick(integer));
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewAndData() {

    }

    @Override
    public void navToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        clickEmitter.onNext(R.id.btn_login);
    }

    public void doOnClick(int id) {
//        CustomToast.show(this,"点了", Toast.LENGTH_SHORT);
        mPresenter.doLogin(loginPhone.getText().toString(),loginPwd.getText().toString(),0);
    }
}
