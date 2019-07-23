package com.zggk.newiroad.handle.bdwaihandle;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.waiHandleItembean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class BDwaihandlePresenter extends BasePresenterImpl<BDwaihandleContract.View> implements BDwaihandleContract.Presenter{
    @Override
    public void add(String JSONS) {

        OkHttpUtils.post()
                .tag(this)
                .addParams("json", JSONS)
                .url(MyApplication.BASEURL+"MobileYhwx")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        waiHandleItembean videoVos2 = JSON.parseObject(response, waiHandleItembean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
