package com.zggk.newiroad.season.seasonrealy;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.Seasonhandlebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SeasonRealyPresenter extends BasePresenterImpl<SeasonRealyContract.View> implements SeasonRealyContract.Presenter{
    @Override
    public void getdate(String jjxyhguid) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("jjxyhguid", jjxyhguid)
                .url(MyApplication.BASEURL + "JjxyhLCMX")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Seasonhandlebean videoVos2 = JSON.parseObject(response, Seasonhandlebean.class);
                        mView.getData(videoVos2);
                    }
                });
    }
}
