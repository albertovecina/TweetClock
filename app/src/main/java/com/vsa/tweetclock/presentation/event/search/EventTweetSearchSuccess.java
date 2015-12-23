package com.vsa.tweetclock.presentation.event.search;

import com.twitter.sdk.android.core.models.Tweet;

/**
 * Created by albertovecinasanchez on 22/12/15.
 */
public class EventTweetSearchSuccess {

    private Tweet tweet;

    public EventTweetSearchSuccess(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

}
