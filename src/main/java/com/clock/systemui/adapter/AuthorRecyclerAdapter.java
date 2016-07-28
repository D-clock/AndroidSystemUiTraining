package com.clock.systemui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clock.systemui.R;
import com.clock.systemui.bean.AuthorInfo;

import java.util.List;

/**
 * Created by Clock on 2016/7/26.
 */
public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder> {

    private static final int LINEAR = 1;
    private static final int GRID = 2;
    private static final int STAGGERED = 3;

    private List<AuthorInfo> mAuthorInfoList;

    private int mType = LINEAR;

    public static AuthorRecyclerAdapter newLinearInstance() {
        AuthorRecyclerAdapter adapter = new AuthorRecyclerAdapter();
        adapter.mType = LINEAR;
        return adapter;
    }

    public static AuthorRecyclerAdapter newGridInstance() {
        AuthorRecyclerAdapter adapter = new AuthorRecyclerAdapter();
        adapter.mType = GRID;
        return adapter;
    }

    public static AuthorRecyclerAdapter newStaggeredInstance(List<AuthorInfo> authorInfoList) {
        AuthorRecyclerAdapter adapter = new AuthorRecyclerAdapter();
        adapter.mType = STAGGERED;
        adapter.mAuthorInfoList = authorInfoList;
        return adapter;
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = null;
        if (mType == LINEAR) {
            childView = inflater.inflate(R.layout.author_card_layout, parent, false);

        } else if (mType == GRID) {
            childView = inflater.inflate(R.layout.author_small_card_layout, parent, false);

        } else if (mType == STAGGERED) {
            childView = inflater.inflate(R.layout.author_small_card_layout, parent, false);

        } else {
            childView = inflater.inflate(R.layout.author_card_layout, parent, false);

        }
        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        if (isStaggered()) {
            AuthorInfo authorInfo = mAuthorInfoList.get(position);
            holder.mNickNameView.setText(authorInfo.getNickName());
            holder.mMottoView.setText(authorInfo.getMotto());
        }
    }

    @Override
    public int getItemCount() {
        if (isStaggered()) {
            if (mAuthorInfoList == null) {
                return 0;
            }
            return mAuthorInfoList.size();
        }
        return 20;
    }

    private boolean isStaggered() {
        return mType == STAGGERED;
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        TextView mNickNameView;
        TextView mMottoView;

        public AuthorViewHolder(View itemView) {
            super(itemView);

            mNickNameView = (TextView) itemView.findViewById(R.id.tv_nickname);
            mMottoView = (TextView) itemView.findViewById(R.id.tv_motto);

        }
    }
}
