package com.cn.joewic.record_custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ColorSlideTextView extends TextView {

    private Paint originalPaint;
    private Paint changedPaint;

    private int progress;
    private String text;

    public ColorSlideTextView(Context context) {
        this(context, null);
    }

    public ColorSlideTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorSlideTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ColorSlideTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        changedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        changedPaint.setTextSize(28);
        changedPaint.setColor(Color.RED);

        originalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        originalPaint.setTextSize(28);
        originalPaint.setColor(Color.BLACK);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        text = getText().toString();
        if(!TextUtils.isEmpty(text)){
            Paint.FontMetrics fontMetrics =
                    originalPaint.getFontMetrics();//获得画笔的FontMetrics，用来计算baseLine。因为drawText的y坐标，代表的是绘制的文字的baseLine的位置
            int baseline =
                    (int) ((getHeight() - fontMetrics.bottom - fontMetrics.top) / 2);//计算出在每格index区域，竖直居中的baseLine值
            drawOrigin(canvas, originalPaint,
                    progress / getWidth(), baseline);
            drawChanged(canvas, changedPaint,
                    progress / getWidth(), baseline);
        }
    }

    private void drawOrigin(Canvas canvas, Paint paint, int startX, int floatY){
        canvas.drawText(text, startX, floatY, paint);
    }

    private void drawChanged(Canvas canvas, Paint paint, int startX, int floatY){
        canvas.drawText(text, startX, floatY, paint);
    }
}
