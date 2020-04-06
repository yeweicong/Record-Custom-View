package com.cn.joewic.record_custom_view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;


import com.cn.joewic.funny_check_view.TickView;
import com.cn.joewic.record_custom_view.view.ArcProgressView;
import com.cn.joewic.record_custom_view.view.CircleProgressView;

import androidx.annotation.Nullable;

public class ViewActivity extends Activity {

    public static final int TYPE_ARC_STEP_PROGRESS = 1;
    public static final int TYPE_FINNY_CHECK_VIEW = 2;
    private LinearLayout containerLayout;

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
        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(500, 500);
        switch (type) {
            case TYPE_ARC_STEP_PROGRESS:
                View arcProgressView = new ArcProgressView(this);
                View circleProgressView = new CircleProgressView(this);

                arcProgressView.setLayoutParams(layoutParams);
                circleProgressView.setLayoutParams(layoutParams);
                containerLayout.addView(arcProgressView);
                containerLayout.addView(circleProgressView);
                afterAddView(arcProgressView, circleProgressView);
                break;

            case TYPE_FINNY_CHECK_VIEW:
                TickView funnyCheckView = new TickView(this);
                funnyCheckView.setLayoutParams(layoutParams);
                containerLayout.addView(funnyCheckView);

                funnyCheckView.setChecked(true);
                break;
        }

    }



    private void afterAddView(View arcProgressView, final View circleProgressView){
        updateArcProgress((ArcProgressView) arcProgressView);
        updateCirProgress((CircleProgressView) circleProgressView);
    }

    private void updateArcProgress(final ArcProgressView view) {
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(0, 3000);
        valueAnimator.setDuration(5000);
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

    private void updateCirProgress(final CircleProgressView view) {
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(0, 100);
        valueAnimator.setDuration(5000);
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
