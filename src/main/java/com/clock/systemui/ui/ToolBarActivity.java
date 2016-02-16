package com.clock.systemui.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clock.systemui.R;
import com.clock.systemui.ui.base.BaseActivity;

public class ToolBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
    }

}
