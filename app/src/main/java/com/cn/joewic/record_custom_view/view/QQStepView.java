package com.cn.joewic.record_custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class QQStepView extends View {

    private Paint innerPaint;
    private Paint outterPaint;
    private Paint textPaint;

    private int strokeWidth = 20;

    private int progress = 0;
    private int maxProgress = 3000;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, 0, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
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
        innerPaint.setColor(Color.GREEN);
        innerPaint.setStrokeWidth(strokeWidth);
        innerPaint.setStrokeCap(Paint.Cap.ROUND);
        innerPaint.setStyle(Paint.Style.STROKE);

        outterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outterPaint.setColor(Color.RED);
        outterPaint.setStrokeWidth(strokeWidth);
        outterPaint.setStrokeCap(Paint.Cap.ROUND);
        outterPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(28);
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
        RectF rectF = new RectF(strokeWidth / 2, strokeWidth / 2, getWidth() -
                strokeWidth / 2,
                getHeight() - strokeWidth / 2);
        canvas.drawArc(rectF, 150, 240, false, outterPaint);


        canvas.drawArc(rectF, 150, ((float) ((float)progress / maxProgress)) * 240,
                false, innerPaint);

        canvas.drawText(String.valueOf(progress), getWidth() / 2, getHeight() / 2,
                textPaint);

    }
}
