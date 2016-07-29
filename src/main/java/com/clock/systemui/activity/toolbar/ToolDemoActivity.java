package com.clock.systemui.activity.toolbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;

public class ToolDemoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_demo);

        findViewById(R.id.btn_toolbar_base).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_zhihu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_toolbar_base) {
            Intent translucentBarIntent = new Intent(this, ToolBarActivity.class);
            startActivity(translucentBarIntent);

        } else if (viewId == R.id.btn_toolbar_zhihu) {
            Intent translucentBarIntent = new Intent(this, ZhiHuActivity.class);
            startActivity(translucentBarIntent);

        }
    }
}
