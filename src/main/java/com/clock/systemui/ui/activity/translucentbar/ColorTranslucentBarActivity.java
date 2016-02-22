package com.clock.systemui.ui.activity.translucentbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.clock.systemui.R;
import com.clock.systemui.ui.activity.base.BaseActivity;
import com.clock.systemui.ui.fragment.ColorTranslucentFragment;

public class ColorTranslucentBarActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_translucent_bar);

        findViewById(R.id.btn_show_toast).setOnClickListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container ,new ColorTranslucentFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_show_toast) {//不要在theme中设置<item name="android:fitsSystemWindows">true</item>
            Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show();
        }
    }
}
