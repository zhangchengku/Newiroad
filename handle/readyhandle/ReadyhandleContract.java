package com.zggk.newiroad.handle.readyhandle;

import android.content.Context;

import com.zggk.newiroad.Bean.ReadyListbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReadyhandleContract {
    interface View extends BaseRequestView {
        void getData(ReadyListbean videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String LXID, String GYDWID, String BHMCID, String DATAID, String ACTION, String PAGESIZE);

    }
}
