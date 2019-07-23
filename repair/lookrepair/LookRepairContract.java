package com.zggk.newiroad.repair.lookrepair;

import android.content.Context;

import com.zggk.newiroad.Bean.Basebean;
import com.zggk.newiroad.Bean.Dingbean;
import com.zggk.newiroad.Bean.Lookdiseasebean;
import com.zggk.newiroad.Bean.repairRKbean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookRepairContract {
    interface View extends BaseRequestView {
        void getData(Lookdiseasebean videoVos2 );
        void getData2(Dingbean videoVos2 );
        void getData3(repairRKbean videoVos2 );
        void getData4(Basebean videoVos2 );
        void getData5(Basebean videoVos2 );
    }

    interface  Presenter extends BasePresenter<View> {//网络
        void testinfo(String id);
        void getZH(String LXCODE,String jd ,String wd ,String gydwid);
        void paifaDisease(String jsons);
        void UpdateBhState(String bhguid,String bhzt,String qdzh,String czfaxx);
        void RovalBhShyj(String bhguid,String bhzt,String shyj,String qdzh,String czfaxx);
    }
}
