package com.wsj.feed.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wsj.feed.R;
import com.wsj.feed.ui.fragments.NewsListFragment;
import com.wsj.feed.models.Feed;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        adapter.addFragment("World News", NewsListFragment.getInstance(Feed.Type.WORLD_NEWS));
        adapter.addFragment("U.S. Business", NewsListFragment.getInstance(Feed.Type.US_BUSINESS));
        adapter.addFragment("Market News", NewsListFragment.getInstance(Feed.Type.MARKET_BUSINESS));
        adapter.addFragment("Technology: What's News", NewsListFragment.getInstance(Feed.Type.TECHNOLOGY));
        adapter.addFragment("Lifestyle", NewsListFragment.getInstance(Feed.Type.LIFESTYLE));
        adapter.addFragment("Opinion", NewsListFragment.getInstance(Feed.Type.OPINION));

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}