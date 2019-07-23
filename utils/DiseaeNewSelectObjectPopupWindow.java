package com.zggk.newiroad.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.tooklkit.Tooklkit;
import com.zggk.newiroad.R;
import com.zggk.newiroad.disease.newdisease.NewdiseaseActivity;
import com.zggk.newiroad.popuwindo.EditSearchAdapter;

import java.util.ArrayList;

/**
 * 病害采集通用选择信息PopupWindow
 * Created by dongxiaoqing on 2018/10/17.
 */

public class DiseaeNewSelectObjectPopupWindow extends PopupWindow {

    private Activity activity;
    private ArrayList<String> listDataStr;
    private ListView listView;
    private TextView vTitleTxt;
    private EditSearchAdapter adapter;
    private DiseaseNewSelectObjectListener listener;
    private String titleStr;

    public DiseaeNewSelectObjectPopupWindow(Activity activity, String titleStr, ArrayList<String> listDataStr, DiseaseNewSelectObjectListener listener){
        this.activity = activity;
        this.listDataStr=listDataStr;
        this.listener=listener;
        this.titleStr=titleStr;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.disease_new_sel_obj_pop, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity)- Tooklkit.dip2px(activity,25));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.mypopwindow_anim_style);
        initViews(contentView);
    }

    private void initViews(View contentView){
        vTitleTxt=(TextView)contentView.findViewById(R.id.disease_new_sel_obj_title_txt);
        listView=contentView.findViewById(R.id.disease_new_sel_obj_list);
        adapter=new EditSearchAdapter(activity,listDataStr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.selectPosition(position);
                close();
            }
        });
        if(!Utils.isNull(titleStr)){
            vTitleTxt.setVisibility(View.VISIBLE);
            vTitleTxt.setText(titleStr);
        }else{
            vTitleTxt.setVisibility(View.GONE);
        }

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if(activity instanceof NewdiseaseActivity){
                    ((NewdiseaseActivity)activity).hideZheZhaoView();
                }
            }
        });
    }

    /**
     * 显示筛选窗口
     */
    public void show(View anchor) {
        if (isShowing()) {
            return;
        }
        if (anchor != null) {
            showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
        }
        if (activity instanceof NewdiseaseActivity) {
            ((NewdiseaseActivity)activity).showZheZhaoView();
        }
    }

    /**
     * 关闭筛选窗口
     */
    public void close() {
        if (isShowing()) {
            dismiss();
        }
    }

    public void notifityData(){
        adapter.notifyDataSetChanged();
    }
}
