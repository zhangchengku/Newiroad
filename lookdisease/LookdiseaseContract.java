package com.zggk.newiroad.lookdisease;

import android.content.Context;

import com.zggk.newiroad.Bean.Lookdiseasebean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookdiseaseContract {
    interface View extends BaseRequestView {
        void getData(Lookdiseasebean videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String id);

    }
}
