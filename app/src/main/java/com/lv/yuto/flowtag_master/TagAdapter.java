package com.lv.yuto.flowtag_master;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.yuto.library.BaseTagAdapter;

/**
 * Created by lv158 on 2016/7/30.
 */
public class TagAdapter extends BaseTagAdapter<Tag> {

    public TagAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(mContext,R.layout.tag_item,null);
            holder=new ViewHolder();
            holder.name= (TextView) view.findViewById(R.id.tv_tag);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
            holder.name.setText(mDataList.get(i).name);
        return view;
    }

    @Override
    public boolean isSelectedPosition(int position) {
        return  mDataList.get(position).isSelected;
    }
    public static class ViewHolder{
        TextView name;
    }
}
