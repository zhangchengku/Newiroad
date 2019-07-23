package com.zggk.newiroad.repair;

import com.zggk.newiroad.Bean.Basebean;
import com.zggk.newiroad.Bean.repairRKbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RepairContract {
    interface View extends BaseRequestView {
        void getData(repairRKbean videoVos2 );
        void getData2(repairRKbean videoVos2 );
        void getData3(Basebean videoVos2 );
        void getData4(Basebean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void addDisease(String gydwid);
        void paifaDisease(String jsons);
        void UpdateBhState(String bhguid,String bhzt,String qdzh,String czfaxx);
        void RovalBhShyj(String bhguid,String bhzt,String shyj,String qdzh,String czfaxx);
    }
}
