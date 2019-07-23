package com.zggk.newiroad.check;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tooklkit.Tooklkit;
import com.zggk.newiroad.R;

import com.zggk.newiroad.check.checkwai.CheckwaiFragment;
import com.zggk.newiroad.repair.repairListFilterAdapter;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-27.
 */

public class CheckPopu extends PopupWindow {
    private Activity activity;
    private ArrayList<String> listDataStr;
    private ListView listView;
    private repairListFilterAdapter adapter;
    private DiseaseNewSelectObjectListener listener;

    public CheckPopu(Activity activity,ArrayList<String> listDataStr,DiseaseNewSelectObjectListener listener){
        this.activity = activity;
        this.listDataStr=listDataStr;
        this.listener=listener;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.disease_list_filter_pop_layout, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        initViews(contentView);
    }

    private void initViews(View contentView){
        listView=contentView.findViewById(R.id.disease_list_filter_pop_list);
        adapter=new repairListFilterAdapter(activity,listDataStr);
        listView.setAdapter(adapter);
        adapter.setCurrentPosition(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.selectPosition(position);
                adapter.setCurrentPosition(position);
                close();
            }
        });

        setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(activity instanceof CheckActivity){
                    ((CheckActivity)activity).hideFilterZheZhaoView();
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
            showAsDropDown(anchor);
        }
        if (activity instanceof CheckActivity) {
            ((CheckActivity)activity).showFilterZheZhaoView();
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


