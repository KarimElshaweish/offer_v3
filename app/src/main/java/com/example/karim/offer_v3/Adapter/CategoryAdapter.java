package com.example.karim.offer_v3.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.karim.offer_v3.EntreyData.CategoryData;
import com.example.karim.offer_v3.R;


/**
 * Created by Karim on 7/30/2018.
 */

public class CategoryAdapter extends BaseAdapter {
    private Context _ctx;
    private int [] Images;
    CategoryData categoryData;
    public CategoryAdapter(Context _ctx, CategoryData categoryData){
        this._ctx=_ctx;
        this.categoryData=categoryData;
    }
    @Override
    public int getCount() {
        return categoryData.coverList.size();
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
                gv=inflater.inflate(R.layout.category_item,null);
                TextView title=gv.findViewById(R.id.title);
                title.setText(categoryData.nameList.get(i).toString());
                ImageView cover=gv.findViewById(R.id.cover);
                Glide.with(_ctx).load(categoryData.coverList.get(i)).into(cover);
                ImageView logo=gv.findViewById(R.id.logo);
                Glide.with(_ctx).load(categoryData.logoList.get(i)).into(logo);

            }else
                gv=view;
        return gv;
    }
}
