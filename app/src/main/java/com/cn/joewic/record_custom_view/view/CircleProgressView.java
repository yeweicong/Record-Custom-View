package com.cn.joewic.record_custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleProgressView extends View {

    private Paint innerPaint;
    private Paint outterPaint;
    private Paint textPaint;

    private int strokeWidth = 20;

    private int progress = 0;
    private int maxProgress = 100;
    private int radius = 200;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, 0, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;

        invalidate();
    }

    private void init() {
        innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint.setColor(Color.parseColor("#FFB6C1"));
        innerPaint.setStrokeWidth(strokeWidth);
        innerPaint.setStrokeCap(Paint.Cap.ROUND);
        innerPaint.setStyle(Paint.Style.STROKE);

        outterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outterPaint.setColor(Color.GRAY);
        outterPaint.setStrokeWidth(strokeWidth);
        outterPaint.setStrokeCap(Paint.Cap.ROUND);
        outterPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(40);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 调用者在布局文件中可能  wrap_content
        // 获取模式 AT_MOST  40DP

        // 宽度高度不一致 取最小值，确保是个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = (getWidth() - (strokeWidth * 2)) / 2;
        int centerY = (getHeight() - (strokeWidth * 2)) / 2;
        canvas.drawCircle(centerX, centerY,
                radius, outterPaint);


        RectF oval = new RectF(centerX - radius, centerY - radius,
                centerX + radius, centerY + radius);

        canvas.drawArc(oval, 90, (((float) ((float)progress / maxProgress)) * 180), false, innerPaint);
        canvas.drawArc(oval, 90, -(((float) ((float)progress / maxProgress)) * 180), false, innerPaint);

        Paint.FontMetrics fontMetrics =
                textPaint.getFontMetrics();//获得画笔的FontMetrics，用来计算baseLine。因为drawText的y坐标，代表的是绘制的文字的baseLine的位置
        int baselineY =
                (int) ((getHeight() - fontMetrics.bottom - fontMetrics.top) / 2);//计算出在每格index区域，竖直居中的baseLine值
        float textWidth = textPaint.measureText(progress + "%");
        canvas.drawText(progress + "%", centerX - (textWidth / 2), baselineY,
                textPaint);

    }
}
