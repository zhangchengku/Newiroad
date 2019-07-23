package com.zggk.newiroad.handle;

import android.content.Context;

import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HandleContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
