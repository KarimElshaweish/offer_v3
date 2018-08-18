package com.example.karim.offer_v3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.karim.offer_v3.R;


/**
 * Created by Karim on 7/29/2018.
 */

public class GVAdapter extends BaseAdapter {
    private Context _ctx;
    private int[]resurce;
    public GVAdapter(Context ctx,int [] resurce) {
        _ctx = ctx;
        this.resurce=resurce;
    }

    @Override
    public int getCount() {
        return resurce.length;
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
        LayoutInflater inflater= (LayoutInflater) _ctx.getSystemService(_ctx.LAYOUT_INFLATER_SERVICE);
        View gv;
        if(view==null){
            gv=new View(_ctx);
            gv=inflater.inflate(R.layout.grid_view_item,null);
            ImageView imgview=gv.findViewById(R.id.img);
            imgview.setImageResource(resurce[i]);
        }else
            gv=view;
        return gv;
    }
}
