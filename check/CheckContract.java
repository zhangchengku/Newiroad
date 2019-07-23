package com.zggk.newiroad.check;

import android.content.Context;

import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckContract {
    interface View extends BaseRequestView {
        void getData(SgRInfo videoVos );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getsx(String GYDWID);
    }
}
