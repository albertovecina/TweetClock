package com.vsa.tweetclock.view;

import com.twitter.sdk.android.core.models.Tweet;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public interface TweetClockView {

    void showTweet(Tweet tweet);

    void showNoTweetsError();

    void setUser(String user);

    void setUserName(String userName);

    void setUserProfileImage(String imageUrl);

    void setTweetCreationDate(String creationDate);

    void setTweetText(String text);

    void setRetweetCount(int count);

    void finish();

}
