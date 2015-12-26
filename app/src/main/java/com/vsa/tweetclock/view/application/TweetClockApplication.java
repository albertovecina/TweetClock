package com.vsa.tweetclock.view.application;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.vsa.tweetclock.R;

import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockApplication extends Application {

    private static ObjectGraph sObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_api_key),
                getString(R.string.twitter_api_secret));
        Fabric.with(this, new Twitter(authConfig));
        sObjectGraph = ObjectGraph.create();
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return sObjectGraph.plus(modules);
    }

}