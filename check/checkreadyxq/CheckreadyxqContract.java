package com.zggk.newiroad.check.checkreadyxq;

import android.content.Context;

import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckreadyxqContract {
    interface View extends BaseRequestView {
        void getData(CheckxqBean videoVos );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getxqdate(String BHID);

    }
}
