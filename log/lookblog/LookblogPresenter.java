package com.zggk.newiroad.log.lookblog;

import com.alibaba.fastjson.JSON;

import com.zggk.newiroad.Bean.LookIntbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookblogPresenter extends BasePresenterImpl<LookblogContract.View> implements LookblogContract.Presenter{
    @Override
    public void testinfo(String RZID) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("rzid", RZID)
                .url(MyApplication.BASEURL+"QueryRzMX")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        LookIntbean videoVos2 = JSON.parseObject(response, LookIntbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
