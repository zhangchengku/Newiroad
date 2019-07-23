package com.zggk.newiroad.examinedone;

import android.content.Context;

import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseView;
import com.zggk.newiroad.Bean.ToExamineDataInfo;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExamineDoneContract {
    interface View extends BaseRequestView {
        void getData(ToExamineDataInfo videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addDisease(String startTime,String lxid,String bhlx,String gydwid,String listtype);

    }
}
