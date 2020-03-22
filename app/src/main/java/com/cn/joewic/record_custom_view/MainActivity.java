package com.cn.joewic.record_custom_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void qqStep(View view) {
        ViewActivity.launch(this, ViewActivity.TYPE_QQ_STEP);
    }
}
