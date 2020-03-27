package com.cn.joewic.record_custom_view.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class FunnyCheckView extends View {

    private Paint staticViewPaint;

    public FunnyCheckView(Context context) {
        super(context);
        init();
    }

    public FunnyCheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FunnyCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FunnyCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        staticViewPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        staticViewPaint.setStrokeWidth(10);
        staticViewPaint.setColor(Color.parseColor("#C0C0C0"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;


    }
}
