package com.zggk.newiroad.disease.lookdiseasedb;

import android.content.Context;

import com.zggk.newiroad.Bean.Dingbean;
import com.zggk.newiroad.Bean.adddiseasebean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookdiseasedbContract {
    interface View extends BaseRequestView {
        void getData(adddiseasebean videoVos2 );
        void getData2(Dingbean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addDisease(String json);
        void getZH(String LXCODE,String jd ,String wd ,String gydwid);
    }
}
