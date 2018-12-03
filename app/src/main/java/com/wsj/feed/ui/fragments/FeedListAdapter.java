package com.wsj.feed.ui.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsj.feed.R;
import com.wsj.feed.models.Feed;

import java.util.ArrayList;
import java.util.List;

class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {

    private List<Feed> feeds;
    private OnClickListener onClickListener;

    FeedListAdapter() {
        feeds = new ArrayList<>();
    }

    void setFeeds(List<Feed> feeds) {
        this.feeds.addAll(feeds);
        notifyDataSetChanged();
    }

    void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed_list, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Feed feed = feeds.get(viewHolder.getAdapterPosition());
        viewHolder.feedDescriptionText.setText(feed.getDescription());
        viewHolder.feedHeadLineText.setText(feed.getTitle());

    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView feedHeadLineText;
        TextView feedDescriptionText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            feedHeadLineText = itemView.findViewById(R.id.item_feed_headline_tv);
            feedDescriptionText = itemView.findViewById(R.id.item_feed_description_tv);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClick(getAdapterPosition(), feeds.get(getAdapterPosition()));
            }
        }
    }

    interface OnClickListener {
        void onClick(int position, Feed feed);
    }
}
