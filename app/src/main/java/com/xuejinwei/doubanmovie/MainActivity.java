package com.xuejinwei.doubanmovie;

import android.os.Bundle;
import android.widget.TextView;

import com.xuejinwei.doubanmovie.base.BaseActivity;
import com.xuejinwei.doubanmovie.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv) TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
}
