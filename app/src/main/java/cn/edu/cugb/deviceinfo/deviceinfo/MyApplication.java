package cn.edu.cugb.deviceinfo.deviceinfo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xuyong on 16/4/7.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
