package com.wsj.feed.api;

import com.wsj.feed.models.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RssApi {

    @GET(UrlStrings.WORLD_NEWS_FEED_URL)
    Call<Rss> getWorldNewsFeed();

    @GET(UrlStrings.US_BUSINESS_FEED_URL)
    Call<Rss> getUsBusinessFeed();

    @GET(UrlStrings.MARKET_NEWS_FEED_URL)
    Call<Rss> getMarketNewsFeed();

    @GET(UrlStrings.TECHNOLOGY_NEWS_FEED_URL)
    Call<Rss> getTechnologyWhatsNewFeed();

    @GET(UrlStrings.LIFESTYLE_FEED_URL)
    Call<Rss> getLifestyleFeed();

    @GET(UrlStrings.OPINION_FEED_URL)
    Call<Rss> getOpinionFeed();
}
