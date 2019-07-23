package com.zggk.newiroad.my;

import android.content.Context;

import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
