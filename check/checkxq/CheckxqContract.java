package com.zggk.newiroad.check.checkxq;

import android.content.Context;

import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckxqContract {
    interface View extends BaseRequestView {
        void getData(CheckxqBean videoVos );
        void getData2(CheckxqBean videoVos );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getxqdate(String BHID);
        void add(String json,String type);
    }
}
