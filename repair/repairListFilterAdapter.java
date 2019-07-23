package com.zggk.newiroad.repair;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zggk.newiroad.R;

import java.util.ArrayList;

/**
 * 模糊搜索列表适配器
 * Created by dongxiaoqing on 2018/9/29.
 */

public class repairListFilterAdapter extends BaseAdapter {

    private ArrayList<String> listData;
    private Context context;
    private int currentPosition=-1;

    public repairListFilterAdapter(Context context, ArrayList<String> listData){
        this.context=context;
        this.listData=listData;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.edit_searc_item,parent,false);
            vh.txtContent=(TextView)convertView.findViewById(R.id.edit_search_item_txt);
            vh.txtContent.setGravity(Gravity.CENTER);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder)convertView.getTag();
        }
        vh.txtContent.setText(listData.get(position));
        if(currentPosition==position){
            vh.txtContent.setTextColor(context.getResources().getColor(R.color.bh_lx_pop_select_text_color));
        }else{
            vh.txtContent.setTextColor(context.getResources().getColor(R.color.text_color_disease_input_dialog_sel));
        }
        return convertView;
    }

    class ViewHolder{
        TextView txtContent;
    }
}

