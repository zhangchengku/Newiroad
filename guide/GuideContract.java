package com.zggk.newiroad.guide;

import android.content.Context;

import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GuideContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
