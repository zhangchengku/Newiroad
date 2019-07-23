package com.zggk.newiroad.season;

import com.zggk.newiroad.Bean.Seasonlistbean;
import com.zggk.newiroad.Bean.seasonbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonContract {
    interface View extends BaseRequestView {
        void getData(seasonbean videoVos2 );
        void getData2(Seasonlistbean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void getbhlxdate();
        void getlist(String Lxid,String Dwid,String sglx,String dataid,String action);
    }
}
