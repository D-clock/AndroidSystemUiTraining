package com.clock.systemui.activity.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;

public class NavigationDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_demo);

        findViewById(R.id.btn_simple_drawer).setOnClickListener(this);
        findViewById(R.id.btn_simple_navigation_drawer).setOnClickListener(this);
        findViewById(R.id.btn_cloud_music).setOnClickListener(this);
        findViewById(R.id.btn_navigation_drawer_anim).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_simple_drawer) {
            Intent simpleDrawerIntent = new Intent(this, SimpleDrawerActivity.class);
            startActivity(simpleDrawerIntent);

        } else if (viewId == R.id.btn_simple_navigation_drawer) {
            Intent simpleNavigationDrawerIntent = new Intent(this, SimpleNavigationDrawerActivity.class);
            startActivity(simpleNavigationDrawerIntent);

        } else if (viewId == R.id.btn_cloud_music) {
            Intent cloudMusicIntent = new Intent(this, CloudMusicActivity.class);
            startActivity(cloudMusicIntent);

        } else if (viewId == R.id.btn_navigation_drawer_anim) {
            Intent animIntent = new Intent(this, NavigationDrawerAnimationActivity.class);
            startActivity(animIntent);

        }
    }
}
