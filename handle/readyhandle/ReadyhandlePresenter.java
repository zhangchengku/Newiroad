package com.zggk.newiroad.handle.readyhandle;

import com.alibaba.fastjson.JSON;

import com.zggk.newiroad.Bean.ReadyListbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ReadyhandlePresenter extends BasePresenterImpl<ReadyhandleContract.View> implements ReadyhandleContract.Presenter{
    private HashMap<String, String> params;

    @Override
    public void getlist(String LXID,String GYDWID,String BHMCID,String DATAID,String ACTION,String PAGESIZE) {
        OkHttpUtils.get()
                .tag(this)
                .addParams("lxcode",LXID)
                .addParams("bhlx",BHMCID )
                .addParams("gydwid",GYDWID )
                .addParams("time1","" )
                .addParams("time2","" )
                .addParams("listtype","1" )
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
                        ReadyListbean videoVos2 = JSON.parseObject(response, ReadyListbean.class);
                        if(mView!=null&&videoVos2!=null){
                            mView.getData(videoVos2);
                        }
                    }
                });
    }

}
