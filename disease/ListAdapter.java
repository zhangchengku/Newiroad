package com.zggk.newiroad.disease;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.zggk.newiroad.R;
import com.zggk.newiroad.Bean.ItemBeanInfo;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-20.
 */


public class ListAdapter extends BaseAdapter {


    private  ArrayList<ItemBeanInfo> listda;
    Context context;
    LayoutInflater inflater;
    public ListAdapter(Context context, ArrayList<ItemBeanInfo> listda) {
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.listda=listda;
    }

    @Override
    public int getCount() {
        return listda.size();
    }

    @Override
    public Object getItem(int position) {
        return listda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.item_adddisease,null);
            holder =new ViewHolder(convertView,position);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nr.setText(listda.get(position).getXmmc());
        holder.editText.setHint(listda.get(position).getJsgs());
        holder.dw.setText(listda.get(position).getJldw());
        return convertView;
    }
    class ViewHolder{
        TextView nr;
        EditText editText;
        TextView dw;
        public ViewHolder(View view,int pisition){
            nr = (TextView) view.findViewById(R.id.nr);
            editText= (EditText) view.findViewById(R.id.ed);
            dw= (TextView) view.findViewById(R.id.dw);
            editText.setTag(pisition);//存tag值
            editText.addTextChangedListener(new TextSwitcher(this));
        }
    }

    class TextSwitcher implements TextWatcher {
        private ViewHolder mHolder;

        public TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.editText.getTag();//取tag值
            listda.get(position).setJsgs(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    public ArrayList<ItemBeanInfo> get(){
        return listda;
    }

}