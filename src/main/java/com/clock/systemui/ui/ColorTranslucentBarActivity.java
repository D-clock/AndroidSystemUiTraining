package com.clock.systemui.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.clock.systemui.R;
import com.clock.systemui.ui.base.BaseActivity;

public class ColorTranslucentBarActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_translucent_bar);

        findViewById(R.id.btn_show_toast).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_show_toast) {//不要在theme中设置<item name="android:fitsSystemWindows">true</item>
            Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show();
        }
    }
}
