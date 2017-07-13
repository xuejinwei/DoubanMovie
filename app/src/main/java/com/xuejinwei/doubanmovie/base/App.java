package com.xuejinwei.doubanmovie.base;

import android.app.Application;

import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by xuejinwei on 2017/7/13.
 * Email:xuejinwei@outlook.com
 */

public class App extends Application{
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.addLogAdapter(new DiskLogAdapter());
    }

    public static App getInstance() {
        return instance;
    }
}
