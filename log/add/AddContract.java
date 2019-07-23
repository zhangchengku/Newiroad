package com.zggk.newiroad.log.add;


import com.zggk.newiroad.Bean.Basebean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddContract {
    interface View extends BaseRequestView {
        void getData(Basebean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);

    }

}
