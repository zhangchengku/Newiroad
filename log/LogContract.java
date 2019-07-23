package com.zggk.newiroad.log;


import com.zggk.newiroad.Bean.LogListbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LogContract {
    interface View extends BaseRequestView {
        void getData(LogListbean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String GYDWID, String STIME, String ETIME, String DATAID, String ACTION, String PAGESIZE);

    }
}
