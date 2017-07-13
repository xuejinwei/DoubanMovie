package com.xuejinwei.doubanmovie;

import android.os.Bundle;

import com.xuejinwei.doubanmovie.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exec(Api().getMovieInTheaters(0, 20),o -> {

        });
    }
}
