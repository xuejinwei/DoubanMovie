package com.xuejinwei.doubanmovie.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.xuejinwei.doubanmovie.base.App;

/**
 * Created by xuejinwei on 2017/7/13.
 * Email:xuejinwei@outlook.com
 * Toast 工具类,使用相同的 Toast 实例共享周期避免 toast 太过频繁
 */

public class ToastUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext = App.getInstance();

    private static Toast sToast;

    public static void show(int resId) {
        show(mContext, mContext.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(mContext, mContext.getResources().getText(resId), duration);
    }

    public static void show(int resId, Object... args) {
        show(mContext, String.format(mContext.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(int duration, int resId, Object... args) {
        show(mContext, String.format(mContext.getResources().getString(resId), args), duration);
    }

    public static void show(CharSequence text) {
        show(mContext, text, Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text, int duration) {
        show(mContext, text, duration);
    }

    public static void show(String format, Object... args) {
        show(mContext, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int duration, String format, Object... args) {
        show(mContext, String.format(format, args), duration);
    }

    /**
     * 使用相同的 Toast 实例共享周期避免 toast 太过频繁
     *
     * @param context  context
     * @param text     文本
     * @param duration 持续时间
     */
    public static void show(Context context, CharSequence text, int duration) {
        if (context != null) {
            if (sToast == null) {
                sToast = Toast.makeText(context, text, duration);
                sToast.show();
            } else {
                sToast.setDuration(duration);
                sToast.setText(text);
                sToast.show();
            }
        }
    }

}
