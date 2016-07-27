package com.clock.systemui.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;
import com.clock.systemui.ui.activity.bartab.BarTabDemoActivity;
import com.clock.systemui.ui.activity.cardview.CardViewDemoActivity;
import com.clock.systemui.ui.activity.collapsing.CollapsingDemoActivity;
import com.clock.systemui.ui.activity.navigation.NavigationDemoActivity;
import com.clock.systemui.ui.activity.recycler.RecyclerDemoActivity;
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
        findViewById(R.id.btn_recycler_demo).setOnClickListener(this);
        findViewById(R.id.btn_card_view).setOnClickListener(this);
        findViewById(R.id.btn_snack_bar).setOnClickListener(this);
        findViewById(R.id.btn_bar_tab).setOnClickListener(this);
        findViewById(R.id.btn_collapsing).setOnClickListener(this);

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

        } else if (viewId == R.id.btn_recycler_demo) {
            Intent intent = new Intent(this, RecyclerDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_card_view) {
            Intent intent = new Intent(this, CardViewDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_snack_bar) {
            Intent intent = new Intent(this, SnackBarDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_bar_tab) {
            Intent intent = new Intent(this, BarTabDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_collapsing) {
            Intent intent = new Intent(this, CollapsingDemoActivity.class);
            startActivity(intent);

        }

    }
}
