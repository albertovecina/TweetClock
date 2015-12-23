package com.vsa.tweetclock.view;

import com.twitter.sdk.android.core.models.Tweet;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public interface TweetClockView {

    void showTweet(Tweet tweet);

    void showLoginError();

    void finish();

}
