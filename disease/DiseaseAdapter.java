package com.zggk.newiroad.disease;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.gson.Gson;

import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.DiseaseListbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;

import com.zggk.newiroad.db.dbhelper.CuringDao;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.DiseaseBaseInfo;
import com.zggk.newiroad.disease.lookdiseasedb.LookdiseasedbActivity;
import com.zggk.newiroad.disease.newdisease.NewdiseaseActivity;
import com.zggk.newiroad.lookdisease.LookdiseaseActivity;
import com.zggk.newiroad.utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * 巡查病害列表适配器
 * Created by dongxiaoqing on 2018/10/10.
 */

public class DiseaseAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DiseaseBaseInfo> listData;
    private ArrayList<DiseaseListbean.BHLISTBean> listDataNetWork;
    private CuringDao curingDao;
    private Gson gson = new Gson();
    private int screenWidth;//屏幕宽度

    public DiseaseAdapter(Context context, ArrayList<DiseaseBaseInfo> listData, ArrayList<DiseaseListbean.BHLISTBean> listDataNetWork) {
        this.context = context;
        this.listData = listData;
        this.listDataNetWork = listDataNetWork;
        curingDao = new CuringDaoImpl(context);
        screenWidth = Tooklkit.getWidth((DiseaseActivity)context)-Tooklkit.dip2px(context,20);
    }

    @Override
    public int getCount() {
        return listData.size() + listDataNetWork.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < listData.size()) {
            return listData.get(position);
        } else {
            return listDataNetWork.get(position - listData.size());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_disease, parent, false);
            vh.roadNameTxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_road_name_txt);
            vh.diseaseNameTxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_disease_name_txt);
            vh.timeTxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_time_txt);
            vh.stateTxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_state_txt);
            vh.coverImg = (ImageView) convertView.findViewById(R.id.disease_adapter_item_cover_img);
            vh.contentLayout=(LinearLayout)convertView.findViewById(R.id.disease_adapter_item_content);
            vh.contentLayout.getLayoutParams().width=screenWidth;
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        DiseaseBaseInfo info;
        DiseaseListbean.BHLISTBean bhDataInfo;
        if (position < listData.size()) {
            info = listData.get(position);
            if (info != null) {
                vh.roadNameTxt.setText(info.getLDMC() + " " + Utils.getZHMCByZH(info.getZH()));
                vh.timeTxt.setText(info.getCJSJ());
                vh.stateTxt.setText("待上传");
                vh.stateTxt.setBackgroundColor(context.getResources().getColor(R.color.disease_flag_text_bg_dsc));
                String imgUrl = info.getTPDZ().split(",")[0];
                Log.e( "getView: ",imgUrl );
                if (!Utils.isNull(imgUrl)) {
                    Glide.with(context)
                            .asBitmap()
                            .apply(MyApplication.app.options)
                            .load(imgUrl)
                            .into(vh.coverImg);
                }
                final int deleteBaseInfoId=info.getBHJBID();
                final int fpos=position;
                final String diseaseBaseInfoJsonStr = gson.toJson(info);
                vh.contentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent intent = new Intent(context, LookdiseasedbActivity.class);
                        intent.putExtra("baseInfoJsonStr", diseaseBaseInfoJsonStr);
                            context.startActivity(intent);

                    }
                });
            }

        } else {
            bhDataInfo = listDataNetWork.get(position - listData.size());
            if (bhDataInfo != null) {
                if (bhDataInfo.getQDZH()!=null){
                    String zhStr = bhDataInfo.getQDZH();
                    if (zhStr.contains(".")) {
                        String zhfStr = zhStr.substring(0, zhStr.indexOf("."));
                        String zhaStr = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
                        if (zhaStr.length() == 1) {
                            zhaStr += "00";
                        } else if (zhaStr.length() == 2) {
                            zhaStr += "0";
                        }
                        vh.roadNameTxt.setText(bhDataInfo.getLXBM() + " K" + zhfStr + "+" + zhaStr);
                    } else {
                        vh.roadNameTxt.setText(bhDataInfo.getLXBM() + " K" + zhStr);
                    }
                }
                vh.timeTxt.setText(bhDataInfo.getDCSJ().replace("T", " "));
                vh.diseaseNameTxt.setText(bhDataInfo.getBHMC());
                vh.stateTxt.setText("已上传");
                vh.stateTxt.setBackgroundColor(context.getResources().getColor(R.color.disease_daishangchuan));
                final String cjbhid = bhDataInfo.getBHID();
                vh.contentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LookdiseaseActivity.class);
                        intent.putExtra("cjbhid", cjbhid);
                        context.startActivity(intent);
                    }
                });
                Glide.with(context)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(bhDataInfo.getTPDZ())
                        .into(vh.coverImg);
            }

        }

        return convertView;
    }

    /**
     * 根据状态获取状态对应的名称
     *
     * @param
     * @return
     */

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    class ViewHolder {
       TextView roadNameTxt;//路线名称
        TextView diseaseNameTxt;//病害名称
        TextView timeTxt;//采集时间
        TextView stateTxt;//病害信息状态
        ImageView coverImg;//采集图片
        LinearLayout contentLayout;
    }

}
