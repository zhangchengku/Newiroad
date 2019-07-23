package com.zggk.newiroad.disease.newdisease;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zggk.newiroad.Bean.Dingbean;
import com.zggk.newiroad.Bean.adddiseasebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class NewdiseasePresenter extends BasePresenterImpl<NewdiseaseContract.View> implements NewdiseaseContract.Presenter{
    @Override
    public void  addDisease(String json ) {

        OkHttpUtils.post()
                .tag(this)
                .addParams("json",json )
                .url(MyApplication.BASEURL+"MobileUpdatebh")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        adddiseasebean videoVos2 = JSON.parseObject(response, adddiseasebean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
    @Override
    public void getZH(String LXCODE,String jd ,String wd ,String gydwid ) {

        OkHttpUtils.get()
                .tag(this)
                .addParams("lxcode",LXCODE )
                .addParams("jd",jd )
                .addParams("wd",wd )
                .addParams("gydwid",gydwid )
                .url(MyApplication.BASEURL+"QueryZHByJwd")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("定位结果",response);
                        Dingbean videoVos2 = JSON.parseObject(response, Dingbean.class);
                        mView.getData2(videoVos2);
                    }
                });

    }
}
