package com.zggk.newiroad.log.looklog;



import com.zggk.newiroad.Bean.AddLogbean;
import com.zggk.newiroad.Bean.Basebean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LooklogContract {
    interface View extends BaseRequestView {
        void getData(Basebean videoVos2);

    }

    interface  Presenter extends BasePresenter<View> {//网络
        void add(String JSONS);

    }

}
