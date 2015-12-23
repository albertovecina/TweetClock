package com.vsa.tweetclock;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "x920zeNNlU3hrIGpkYkbqcJbW";
    private static final String TWITTER_SECRET = "sPhA2mjklQ06xJb6RUzXQSxj4wrWvFmrJ8hUv3fH1CVGalYTiq";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
