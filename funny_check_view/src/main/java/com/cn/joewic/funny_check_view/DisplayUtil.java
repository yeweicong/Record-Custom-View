package com.cn.joewic.funny_check_view;

import android.content.Context;

class DisplayUtil {
    static int dp2px(Context context, float dpValue) {
        if (context == null) return (int) (dpValue * 1.5f + 0.5f);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
