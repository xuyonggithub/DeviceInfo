package cn.edu.cugb.deviceinfo.deviceinfo;

import android.support.v4.app.Fragment;

import com.baidu.mobstat.StatService;

/**
 * Created by xuyong on 16/4/4.
 */
public class BaseFragment extends  Fragment{

    @Override
    public void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatService.onResume(this);
    }
}
