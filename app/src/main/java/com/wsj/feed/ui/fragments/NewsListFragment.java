package com.wsj.feed.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wsj.feed.R;
import com.wsj.feed.api.NetworkAccessor;
import com.wsj.feed.api.OnNetworkCallListener;
import com.wsj.feed.models.Feed;
import com.wsj.feed.models.Rss;
import com.wsj.feed.ui.webviewactivity.WebViewActivity;
import com.wsj.feed.utils.InternetConnectivityUtils;

public class NewsListFragment extends Fragment implements OnNetworkCallListener, FeedListAdapter.OnClickListener {

    public static NewsListFragment getInstance(Feed.Type feedType) {
        NewsListFragment newsListFragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(FEED_TYPE_KEY, feedType);
        newsListFragment.setArguments(bundle);
        return newsListFragment;
    }

    private static final String FEED_TYPE_KEY = "feed_type_key";
    private FeedListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_news_list, container, false);

        RecyclerView feedListView = rootView.findViewById(R.id.feed_recycler_view);
        feedListView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FeedListAdapter();
        feedListView.setAdapter(adapter);
        adapter.setOnClickListener(this);

        Feed.Type feedType = (Feed.Type) getArguments().getSerializable(FEED_TYPE_KEY);
        if (InternetConnectivityUtils.isNetworkAvailable(getContext())) {
            NetworkAccessor.getInstance().getFeed(this, feedType);
        } else {
            Toast.makeText(getContext(), "Check internet connectivity!", Toast.LENGTH_LONG).show();
        }

        return rootView;
    }

    @Override
    public void onSuccessResult(Rss rss, Feed.Type feedType) {
        if (adapter != null) {
            adapter.setFeeds(rss.getFeeds());
        }
    }

    @Override
    public void onErrorResult(String error) {

    }

    @Override
    public void onClick(int position, Feed feed) {
        WebViewActivity.open(getContext(), feed);
    }
}
