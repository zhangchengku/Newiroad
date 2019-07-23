package com.zggk.newiroad.season.seasoncheck;

import android.content.Context;

import com.zggk.newiroad.Bean.SUCCESBEAN;
import com.zggk.newiroad.Bean.Seasonhandlebean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonCheckContract {
    interface View extends BaseRequestView {
        void getData(Seasonhandlebean videoVos2 );
        void getData2(SUCCESBEAN videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getdate(String jjxyhguid);
        void addTask(String guid,String state);
    }
}
