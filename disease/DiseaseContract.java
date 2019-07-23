package com.zggk.newiroad.disease;

import android.content.Context;

import com.zggk.newiroad.Bean.DiseaseListbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DiseaseContract {
    interface View extends BaseRequestView {
        void getData(DiseaseListbean videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String GYDWID ,String STIME,String ETIME,String DATAID,String ACTION,String PAGESIZE);

    }
}

