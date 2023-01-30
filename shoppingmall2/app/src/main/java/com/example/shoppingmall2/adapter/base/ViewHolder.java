package com.example.shoppingmall2.adapter.base;

import android.util.SparseArray;
import android.view.View;

//简化所有的ViewHolder
public class ViewHolder {

    public static <T extends View> T getView(View view, int id, View.OnClickListener listener){
        SparseArray<View> viewHolder =(SparseArray<View>) view.getTag();
        if(viewHolder==null){
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View v = viewHolder.get(id);
        if(v==null){
            v = view.findViewById(id);
            if(listener != null) v.setOnClickListener(listener);
            viewHolder.put(id,v);
        }
        return (T) v;
    }
}
