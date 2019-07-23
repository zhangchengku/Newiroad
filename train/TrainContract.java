package com.zggk.newiroad.train;

import android.content.Context;

import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TrainContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
