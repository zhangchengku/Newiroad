package com.zggk.newiroad.check.checkwai;

import android.content.Context;

import com.zggk.newiroad.Bean.checkwaibean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckwaiContract {
    interface View extends BaseRequestView {
        void getData(checkwaibean videoVos2 );


    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getlist(String LXID,String GYDWID,String BHID,String STIME,String ETIME,String DATAID,String ACTION,String PAGESIZE);



    }
}
