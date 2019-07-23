package com.zggk.newiroad.season;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.Seasonlistbean;
import com.zggk.newiroad.Bean.seasonbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonPresenter extends BasePresenterImpl<SeasonContract.View> implements SeasonContract.Presenter {


    @Override
    public void getbhlxdate() {
        OkHttpUtils.get()
                .tag(this)
                .addParams("gydwid", MyApplication.spUtils.getString("dqgydwid"))
                .url(MyApplication.BASEURL + "MobileJjxyhRkLC")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        seasonbean videoVos2 = JSON.parseObject(response, seasonbean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    @Override
    public void getlist(String Lxid,String Dwid,String sglx,String dataid,String action) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("lxcode", Lxid)
                .addParams("gydwid", Dwid)
                .addParams("sglx", sglx)
                .addParams("dataid", dataid)
                .addParams("action", action)
                .addParams("pagesize", "10")
                .url(MyApplication.BASEURL + "QueryJjxyhLC")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Seasonlistbean videoVos2 = JSON.parseObject(response, Seasonlistbean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }

}
