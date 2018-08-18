package com.example.karim.offer_v3.CustomeTools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Karim on 8/10/2018.
 */

public class RoundCoronorImageView extends android.support.v7.widget.AppCompatImageView {
    private float radius=18.0f;
    private Path path;
    private RectF rect;


    public RoundCoronorImageView(Context context) {
        super(context);
        init();
    }

    public RoundCoronorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundCoronorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        path=new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect=new RectF(0,0,this.getWidth(),this.getHeight());
        path.addRoundRect(rect,radius,radius,Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);

    }
}
