package com.clock.systemui.ui.activity.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.clock.systemui.R;
import com.clock.systemui.ui.adapter.AuthorRecyclerAdapter;

public class RecyclerDemoActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_layout_option);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_linear) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerDemoActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    mRecyclerView.setAdapter(new AuthorRecyclerAdapter());

                } else if (checkedId == R.id.rb_grid) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(RecyclerDemoActivity.this, 2);
                    gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);


                } else if (checkedId == R.id.rb_staggered) {

                }

            }
        });
        RadioButton linearRadioButton = (RadioButton) findViewById(R.id.rb_linear);
        linearRadioButton.setChecked(true);
    }
}
