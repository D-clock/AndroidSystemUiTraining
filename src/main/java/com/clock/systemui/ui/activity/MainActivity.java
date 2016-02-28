package com.clock.systemui.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;
import com.clock.systemui.ui.activity.navigation.CloudMusicActivity;
import com.clock.systemui.ui.activity.navigation.NavigationDrawerAnimationActivity;
import com.clock.systemui.ui.activity.navigation.SimpleDrawerActivity;
import com.clock.systemui.ui.activity.navigation.SimpleNavigationDrawerActivity;
import com.clock.systemui.ui.activity.toolbar.ToolBarActivity;
import com.clock.systemui.ui.activity.toolbar.ZhiHuActivity;
import com.clock.systemui.ui.activity.translucentbar.BestTranslucentBarActivity;
import com.clock.systemui.ui.activity.translucentbar.ColorTranslucentBarActivity;
import com.clock.systemui.ui.activity.translucentbar.ImageTranslucentBarActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_image_translucent).setOnClickListener(this);
        findViewById(R.id.btn_color_translucent).setOnClickListener(this);
        findViewById(R.id.btn_best_translucent).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_base).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_zhihu).setOnClickListener(this);
        findViewById(R.id.btn_simple_drawer).setOnClickListener(this);
        findViewById(R.id.btn_simple_navigation_drawer).setOnClickListener(this);
        findViewById(R.id.btn_cloud_music).setOnClickListener(this);
        findViewById(R.id.btn_navigation_drawer_anim).setOnClickListener(this);

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

        } else if (viewId == R.id.btn_best_translucent) {
            Intent translucentBarIntent = new Intent(this, BestTranslucentBarActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_toolbar_base) {
            Intent translucentBarIntent = new Intent(this, ToolBarActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_toolbar_zhihu) {
            Intent translucentBarIntent = new Intent(this, ZhiHuActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_simple_drawer) {
            Intent simpleDrawerIntent = new Intent(this, SimpleDrawerActivity.class);
            startActivity(simpleDrawerIntent);

        } else if (viewId == R.id.btn_simple_navigation_drawer) {
            Intent simpleNavigationDrawerIntent = new Intent(this, SimpleNavigationDrawerActivity.class);
            startActivity(simpleNavigationDrawerIntent);

        } else if (viewId == R.id.btn_cloud_music) {
            Intent cloudMusicIntent = new Intent(this, CloudMusicActivity.class);
            startActivity(cloudMusicIntent);

        } else if (viewId == R.id.btn_navigation_drawer_anim){
            Intent animIntent = new Intent(this, NavigationDrawerAnimationActivity.class);
            startActivity(animIntent);

        }
    }
}
