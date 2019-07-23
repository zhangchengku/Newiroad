package com.zggk.newiroad.handle.bdwaihandle;

import android.content.Context;

import com.zggk.newiroad.Bean.waiHandleItembean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BDwaihandleContract {
    interface View extends BaseRequestView {
        void getData(waiHandleItembean videoVos2 );

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);

    }
}
