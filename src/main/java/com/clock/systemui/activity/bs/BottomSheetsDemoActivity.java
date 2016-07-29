package com.clock.systemui.activity.bs;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.clock.systemui.R;
import com.clock.systemui.adapter.RecyclerItemAdapter;

public class BottomSheetsDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheets_demo);

        findViewById(R.id.btn_show_dialog).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_show_dialog) {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            View contentView = View.inflate(this, R.layout.bottom_sheets_layout, null);
            RecyclerView itemView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            itemView.setLayoutManager(layoutManager);
            RecyclerView.Adapter adapter = new RecyclerItemAdapter();
            itemView.setAdapter(adapter);
            dialog.setContentView(contentView);
            dialog.show();
        }
    }
}
