package com.wsj.feed.api;

import com.wsj.feed.models.Feed;
import com.wsj.feed.models.Rss;

public interface OnNetworkCallListener {

   void onSuccessResult(Rss object, Feed.Type feedType);
   void onErrorResult(String error);
}
