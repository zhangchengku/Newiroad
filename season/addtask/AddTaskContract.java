package com.zggk.newiroad.season.addtask;

import android.content.Context;

import com.zggk.newiroad.Bean.SUCCESBEAN;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddTaskContract {
    interface View extends BaseRequestView {
        void getData(SUCCESBEAN videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addTask(String json);

    }
}
