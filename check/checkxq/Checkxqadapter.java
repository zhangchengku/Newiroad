package com.zggk.newiroad.check.checkxq;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.R;

import java.util.List;

/**
 * Created by 张成昆 on 2019-3-26.
 */


public class Checkxqadapter extends BaseAdapter {
    private Context mContext;
    private List<CheckxqBean.LXUPDATEISTBean> mDatas;
    private LayoutInflater mInflater;
    private String date;


    public Checkxqadapter(Context mContext, List<CheckxqBean.LXUPDATEISTBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) { //下拉项布局
            convertView = mInflater.inflate(R.layout.item_relx, null);
            holder = new ViewHolder();
            //获取布局文件
            holder.text3 = (TextView) convertView.findViewById(R.id.text3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }//0：采集阶段，2：施工阶段，3：验收阶段，4：考核阶段
        final CheckxqBean.LXUPDATEISTBean dataBean = mDatas.get(position);
        if (dataBean.getType().equals("1")){
            date = "施工阶段";
        }else if (dataBean.getType().equals("2")){
            date = "派发阶段";
        }else if (dataBean.getType().equals("3")){
            date = "采集阶段";
        }
        holder.text3.setText(Html.fromHtml("<font color=\"#666666\">" + "由" + "</font>" +replaceNull(dataBean.getDW()) + "<font color=\"#666666\">" + "在" + "</font>"+date+"<font color=\"#666666\">" + "修改为" + "</font>"+replaceNull(dataBean.getSL())));
        return convertView;
    }

    class ViewHolder {
        public TextView text3;
    }
    public static String replaceNull(String str) {
        if (str == null||str.equals("null")) {
            return "";
        } else {
            return str;
        }

    }
}

