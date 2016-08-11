package com.clock.systemui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clock.systemui.R;
import com.clock.systemui.bean.AuthorInfo;

import java.util.Collections;
import java.util.List;

/**
 * Created by Clock on 2016/7/26.
 */
public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.AuthorViewHolder> {

    private List<AuthorInfo> mAuthorInfoList;
    /**
     * item view 的类型是否是小类型的
     */
    private boolean mIsSmall = false;

    public AuthorRecyclerAdapter(List<AuthorInfo> authorInfoList) {
        this.mAuthorInfoList = authorInfoList;
    }

    public void setSmallType(boolean isSmall) {
        this.mIsSmall = isSmall;
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = null;
        if (mIsSmall) {
            childView = inflater.inflate(R.layout.author_small_card_layout, parent, false);

        } else {
            childView = inflater.inflate(R.layout.author_card_layout, parent, false);

        }
        AuthorViewHolder viewHolder = new AuthorViewHolder(childView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        AuthorInfo authorInfo = mAuthorInfoList.get(position);
        holder.mPortraitView.setImageResource(authorInfo.getPortrait());
        holder.mNickNameView.setText(authorInfo.getNickName());
        holder.mMottoView.setText(authorInfo.getMotto());
    }

    @Override
    public int getItemCount() {
        if (mAuthorInfoList == null) {
            return 0;
        }
        return mAuthorInfoList.size();
    }

    /**
     * 移动Item
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition) {
        //做数据的交换
        if (fromPosition < toPosition) {
            for (int index = fromPosition; index < toPosition; index++) {
                Collections.swap(mAuthorInfoList, index, index + 1);
            }
        } else {
            for (int index = fromPosition; index > toPosition; index--) {
                Collections.swap(mAuthorInfoList, index, index - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 滑动Item
     *
     * @param position
     */
    public void removeItem(int position) {
        mAuthorInfoList.remove(position);//删除数据
        notifyItemRemoved(position);
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        ImageView mPortraitView;
        TextView mNickNameView;
        TextView mMottoView;

        public AuthorViewHolder(View itemView) {
            super(itemView);

            mPortraitView = (ImageView) itemView.findViewById(R.id.iv_portrait);
            mNickNameView = (TextView) itemView.findViewById(R.id.tv_nickname);
            mMottoView = (TextView) itemView.findViewById(R.id.tv_motto);

        }
    }
}
