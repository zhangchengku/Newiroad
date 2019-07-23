package com.zggk.newiroad.check;

/**
 * Created by 10270 on 2018/11/9.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zggk.newiroad.Bean.checkwaibean;
import com.zggk.newiroad.R;


import java.util.List;

/**
 * Created by SNMSUNG on 2017/11/22.
 */

public class Checkadapter extends BaseAdapter {
    private Context mContext;
    private List<checkwaibean.BHLISTBean> mDatas;
    private LayoutInflater mInflater;
    public boolean flage = false;

    public Checkadapter(Context mContext, List<checkwaibean.BHLISTBean> mDatas) {
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
            convertView = mInflater.inflate(R.layout.check_wai_item, null);
            holder = new ViewHolder();
            //获取布局文件
            holder.checkboxOperateData = (CheckBox) convertView.findViewById(R.id.cb);
            holder.statetxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_state_txt);
            holder.coverimg = (ImageView) convertView.findViewById(R.id.disease_adapter_item_cover_img);
            holder.roadnametxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_road_name_txt);
            holder.unitnametxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_unit_name_txt);
            holder.diseasenametxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_disease_name_txt);
            holder.itemtimetxt = (TextView) convertView.findViewById(R.id.disease_adapter_item_time_txt);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final checkwaibean.BHLISTBean dataBean = mDatas.get(position);
        if (dataBean != null) {
            if (dataBean.getBHZT().equals("4")) {
                holder.statetxt.setText("待验收");
                holder.statetxt.setBackgroundColor( mContext.getResources().getColor(R.color.disease_flag_text_bg_dsc));
            }else if (dataBean.getBHZT().equals("5")) {
                holder.statetxt.setText("已验收");
                holder.statetxt.setBackgroundColor( mContext.getResources().getColor(R.color.disease_flag_text_bg_ypf_yys_ywx));
            }else if (dataBean.getBHZT().equals("6")){
                holder.statetxt.setText("验收退回");
                holder.statetxt.setBackgroundColor( mContext.getResources().getColor(R.color.pf_xin_bing_hai_text_color));
            }
            if (dataBean.getTPDZ() != null) {
                    Glide.with(mContext)
                            .asBitmap()
                            .load(dataBean.getTPDZ())
                            .into(holder.coverimg);
            }

            holder.roadnametxt.setText(dataBean.getLXBM());
            holder.diseasenametxt.setText(dataBean.getBHMC());
            holder.itemtimetxt.setText(replaceIM(dataBean.getXFSJ()) );
            //根据isSelected来设置CheckBox的显示显示状况
            if (flage) {
                holder.checkboxOperateData.setVisibility(View.VISIBLE);
            } else {
                holder.checkboxOperateData.setVisibility(View.GONE);
            }
            holder.checkboxOperateData.setChecked(dataBean.isCheck);
            holder.checkboxOperateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.isCheck) {
                        dataBean.setCheck(false);
                    } else {
                        dataBean.setCheck(true);
                    }
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        public CheckBox checkboxOperateData;
        public TextView statetxt;
        public ImageView coverimg;
        public TextView roadnametxt;
        public TextView unitnametxt;
        public TextView diseasenametxt;
        public TextView itemtimetxt;
    }
    public static String replaceIM(String str) {
        if (str == null) {
            return "";
        }else {
            if (str.contains("T")){
                return str.replace("T"," ");
            }else {
                return str;
            }
        }

    }
}

