package com.clock.systemui.activity.translucent;

import android.os.Bundle;

import com.clock.systemui.R;

/**
 * TranslucentBar最好的实现方式
 *
 * @author Clock
 * @since 2016-02-22
 */
public class BestTranslucentBarActivity extends TranslucentBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_best_translucent_bar;
    }
}
