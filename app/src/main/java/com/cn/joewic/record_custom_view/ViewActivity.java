package com.cn.joewic.record_custom_view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;


import com.cn.joewic.record_custom_view.view.QQStepView;

import androidx.annotation.Nullable;

public class ViewActivity extends Activity {

    public static final int TYPE_QQ_STEP = 1;
    private FrameLayout containerLayout;

    public static void launch(Activity activity, int type){
        Intent intent = new Intent(activity, ViewActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        containerLayout = findViewById(R.id.activity_view_container);

        dispatchIntent();
    }

    private void dispatchIntent() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        View targetView = null;
        switch (type){
            case TYPE_QQ_STEP:
                targetView = new QQStepView(this);

                break;
                default:
                    break;
        }

        if(targetView != null){
            containerLayout.addView(targetView);
        }

        afterAddView(type, targetView);
    }

    private void afterAddView(int type, final View view){
        switch (type){
            case TYPE_QQ_STEP:
                updateQQStep((QQStepView) view);
                break;
        }
    }

    private void updateQQStep(final QQStepView view) {
        // 属性动画 后面讲的内容
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(0, 3000);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentStep = (int) animation.getAnimatedValue();
                view.setProgress(currentStep);
            }
        });
        valueAnimator.start();
    }
}
