package com.clock.systemui.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;
import com.clock.systemui.ui.toolbar.NetEaseMusicActivity;
import com.clock.systemui.ui.toolbar.ToolBarActivity;
import com.clock.systemui.ui.toolbar.ZhiHuActivity;
import com.clock.systemui.ui.translucentbar.ColorTranslucentBarActivity;
import com.clock.systemui.ui.translucentbar.ImageTranslucentBarActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_image_translucent).setOnClickListener(this);
        findViewById(R.id.btn_color_translucent).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_base).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_zhihu).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_music).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_image_translucent) {
            Intent translucentBarIntent = new Intent(this, ImageTranslucentBarActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_color_translucent) {
            Intent translucentBarIntent = new Intent(this, ColorTranslucentBarActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_toolbar_base) {
            Intent translucentBarIntent = new Intent(this, ToolBarActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_toolbar_zhihu) {
            Intent translucentBarIntent = new Intent(this, ZhiHuActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_toolbar_music) {
            Intent translucentBarIntent = new Intent(this, NetEaseMusicActivity.class);
            startActivity(translucentBarIntent);

        }
    }
}
