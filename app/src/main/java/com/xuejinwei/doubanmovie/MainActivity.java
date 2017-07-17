package com.xuejinwei.doubanmovie;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xuejinwei.doubanmovie.base.BaseActivity;
import com.xuejinwei.doubanmovie.utils.ToastUtil;

public class MainActivity extends BaseActivity {


    private Toolbar  mToolbar;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mTv.setOnClickListener(v -> {
            exec(Api().getMovieInTheaters(0, 20), o -> {
                mTv.setText(o.toString());
            }, e -> {

            }, throwable -> {
                ToastUtil.show(throwable.getMessage());
                return false;
            });
        });
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTv = (TextView) findViewById(R.id.tv);
    }
}
