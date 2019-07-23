package com.zggk.newiroad.handle.waihandle;

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

public class WaihandlePresenter extends BasePresenterImpl<WaihandleContract.View> implements WaihandleContract.Presenter{
    @Override
    public void getlist(String LXID,String GYDWID,String BHMCID,String DATAID,String ACTION,String PAGESIZE) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("lxcode",LXID )
                .addParams("bhlx",BHMCID )
                .addParams("gydwid",GYDWID )
                .addParams("starttime","" )
                .addParams("endtime","" )
                .addParams("listtype","0" )
                .addParams("dataid",DATAID )
                .addParams("action",ACTION )
                .addParams("pagesize",PAGESIZE )
                .url(MyApplication.BASEURL+"QueryYhwxList")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (!"".equals(id+"")&&id==0){
                            mView.onRequestError(id+"");
                        }
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        waiHandleItembean videoVos2 = JSON.parseObject(response, waiHandleItembean.class);
                        mView.getData(videoVos2);
                    }
                });

    }
}
