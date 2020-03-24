package com.cn.joewic.record_custom_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private LinearLayout main_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        main_container = findViewById(R.id.main_container);

        addButton("圆弧计步数", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewActivity.launch(MainActivity.this, ViewActivity.TYPE_ARC_STEP_PROGRESS);
            }
        });

        addButton("圆弧对称计步数", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewActivity.launch(MainActivity.this, ViewActivity.TYPE_CIRCLE_STEP_PROGRESS);
            }
        });
    }

    private void addButton(String text, View.OnClickListener onClickListener){
        Button button = new Button(this);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(layoutParams);
        button.setTextSize(18);
        button.setText(text);
        button.setOnClickListener(onClickListener);

        main_container.addView(button);
    }
}
