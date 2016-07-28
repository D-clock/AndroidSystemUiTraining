package com.clock.systemui.activity.snackbar;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.clock.systemui.R;

public class SnackBarDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private View mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar_demo);

        mContainer = findViewById(R.id.container);

        findViewById(R.id.btn_simple_snack_bar).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_simple_snack_bar) {
            Snackbar.make(mContainer, "Hello SnackBar!", Snackbar.LENGTH_LONG).setAction("Clock", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something
                }
            }).show();
        }
    }
}
