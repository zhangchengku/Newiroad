package com.zggk.newiroad.log.lookblog;


import com.zggk.newiroad.Bean.LookIntbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookblogContract {
    interface View extends BaseRequestView {
        void getData(LookIntbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String RZID);

    }
}
