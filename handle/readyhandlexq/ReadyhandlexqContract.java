package com.zggk.newiroad.handle.readyhandlexq;

import android.content.Context;

import com.zggk.newiroad.Bean.ReadyhandxqleInfo;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReadyhandlexqContract {
    interface View extends BaseRequestView {
        void getData(ReadyhandxqleInfo videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getxqdate(String BHID);

    }
}
