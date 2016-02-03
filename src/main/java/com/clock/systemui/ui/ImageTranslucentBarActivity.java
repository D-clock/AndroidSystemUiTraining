package com.clock.systemui.ui;

import android.os.Bundle;
import android.view.Window;

import com.clock.systemui.R;
import com.clock.systemui.ui.base.BaseActivity;

/**
 * Translucent System Bar特性的使用
 *
 * @author Clock
 * @since 2016-02-03
 */
public class ImageTranslucentBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_translucent_bar);
    }
}
