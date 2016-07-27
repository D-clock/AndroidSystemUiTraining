package com.clock.systemui.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clock.systemui.R;

/**
 * Created by Clock on 2016/7/26.
 */
public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder> {

    private static final int LINEAR = 1;
    private static final int GRID = 2;
    private static final int STAGGERED = 3;

    private int type = LINEAR;

    public static AuthorRecyclerAdapter newLinearInstance() {
        AuthorRecyclerAdapter adapter = new AuthorRecyclerAdapter();
        adapter.type = LINEAR;
        return adapter;
    }

    public static AuthorRecyclerAdapter newGridInstance() {
        AuthorRecyclerAdapter adapter = new AuthorRecyclerAdapter();
        adapter.type = GRID;
        return adapter;
    }

    public static AuthorRecyclerAdapter newStraggeredInstance() {
        AuthorRecyclerAdapter adapter = new AuthorRecyclerAdapter();
        adapter.type = STAGGERED;
        return adapter;
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = null;
        if (type == LINEAR) {
            childView = inflater.inflate(R.layout.author_card_layout, parent, false);
        } else if (type == GRID) {
            childView = inflater.inflate(R.layout.author_small_card_layout, parent, false);
        } else if (type == STAGGERED) {
            childView = inflater.inflate(R.layout.author_small_card_layout, parent, false);
        } else {
            childView = inflater.inflate(R.layout.author_card_layout, parent, false);
        }

        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {

        public AuthorViewHolder(View itemView) {
            super(itemView);
        }
    }
}
