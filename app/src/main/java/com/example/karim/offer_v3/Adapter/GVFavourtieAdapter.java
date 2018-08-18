package com.example.karim.offer_v3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.karim.offer_v3.R;


/**
 * Created by Karim on 8/10/2018.
 */

public class GVFavourtieAdapter extends BaseAdapter {
    Context _ctx;
    public  GVFavourtieAdapter(Context _ctx){
        this._ctx=_ctx;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View Gv;
        LayoutInflater inflater= (LayoutInflater) _ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null){
            Gv=new View(_ctx);
            Gv=inflater.inflate(R.layout.favourite_grid_item,null);

        }else
            Gv=view;
        return Gv;
    }
}
