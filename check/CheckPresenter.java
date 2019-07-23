package com.zggk.newiroad.check;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CheckPresenter extends BasePresenterImpl<CheckContract.View> implements CheckContract.Presenter {
    @Override
    public void getsx(String GYDWID) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", GYDWID)
                .addParams("lxid","")
                .addParams("bhid", "")
                .url(MyApplication.BASEURL+"QueryYsjl")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        SgRInfo videoVos = JSON.parseObject(response, SgRInfo.class);
                        mView.getData(videoVos);

                    }
                });
    }
}