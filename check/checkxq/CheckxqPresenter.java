package com.zggk.newiroad.check.checkxq;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckxqPresenter extends BasePresenterImpl<CheckxqContract.View> implements CheckxqContract.Presenter {
    @Override
    public void getxqdate(String BHID) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("bhid", BHID)
                .url(MyApplication.BASEURL+"QueryBhmxByys")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        CheckxqBean videoVos2 = JSON.parseObject(response, CheckxqBean.class);
                        mView.getData(videoVos2);
                    }
                });
    }
    @Override
    public void add(String json,String type) {
        OkHttpUtils.post()
                .tag(this)
                .addParams("json", json)
                .addParams("sfhg", type)
                .url(MyApplication.BASEURL+"MobileYsjl")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        CheckxqBean videoVos2 = JSON.parseObject(response, CheckxqBean.class);
                        mView.getData2(videoVos2);
                    }
                });
    }
}