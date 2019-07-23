package com.zggk.newiroad.handle.inwaihandle;

import android.content.Context;

import com.zggk.newiroad.Bean.Dingbean;
import com.zggk.newiroad.Bean.waiHandleItembean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class INwaihandleContract {
    interface View extends BaseRequestView {
        void getData(waiHandleItembean videoVos2 );
        void getData2(Dingbean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);
        void getZH(String LXCODE,String jd ,String wd ,String gydwid);
    }
}
