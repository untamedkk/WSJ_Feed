package com.wsj.feed.api;

import com.wsj.feed.models.Feed;
import com.wsj.feed.models.Rss;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetworkAccessor {

    private static NetworkAccessor instance;

    private NetworkAccessor() {

    }

    public static NetworkAccessor getInstance() {
        if (instance == null) {
            instance = new NetworkAccessor();
        }
        return instance;
    }

    private RssApi getRetrofit() {
        RssApi retrofit = null;
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(40, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlStrings.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build().create(RssApi.class);
        }
        return retrofit;
    }

    public void getFeed(final OnNetworkCallListener listener, final Feed.Type feedType) {
        Call<Rss> call = getFeed(feedType);
        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                listener.onSuccessResult(response.body(), feedType);
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                listener.onErrorResult(t.getMessage());
            }
        });
    }

    private Call<Rss> getFeed(Feed.Type feedType) {
        Call<Rss> call = null;
        switch (feedType) {
            case OPINION:
                call = getRetrofit().getOpinionFeed();
                break;
            case LIFESTYLE:
                call = getRetrofit().getLifestyleFeed();
                break;
            case TECHNOLOGY:
                call = getRetrofit().getTechnologyWhatsNewFeed();
                break;
            case WORLD_NEWS:
                call = getRetrofit().getWorldNewsFeed();
                break;
            case US_BUSINESS:
                call = getRetrofit().getUsBusinessFeed();
                break;
            case MARKET_BUSINESS:
                call = getRetrofit().getMarketNewsFeed();
                break;
        }
        return call;
    }
}