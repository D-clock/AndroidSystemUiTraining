package com.clock.systemui.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;
import com.clock.systemui.ui.activity.cardview.SimpleCardActivity;
import com.clock.systemui.ui.activity.navigation.NavigationDemoActivity;
import com.clock.systemui.ui.activity.snackbar.SnackBarDemoActivity;
import com.clock.systemui.ui.activity.toolbar.ToolDemoActivity;
import com.clock.systemui.ui.activity.translucent.TranslucentDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_translucent_demo).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_demo).setOnClickListener(this);
        findViewById(R.id.btn_navigation_demo).setOnClickListener(this);
        findViewById(R.id.btn_simple_card).setOnClickListener(this);
        findViewById(R.id.btn_snack_bar).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_translucent_demo) {
            Intent intent = new Intent(this, TranslucentDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_toolbar_demo) {
            Intent intent = new Intent(this, ToolDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_navigation_demo) {
            Intent intent = new Intent(this, NavigationDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_simple_card) {
            Intent intent = new Intent(this, SimpleCardActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_snack_bar) {
            Intent intent = new Intent(this, SnackBarDemoActivity.class);
            startActivity(intent);

        }

    }
}
