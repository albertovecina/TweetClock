package com.vsa.tweetclock.view;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public interface TweetClockView {

    void showProgress();

    void hideProgress();

    void showNoTweetsError();

    void setUser(String user);

    void setUserName(String userName);

    void setUserProfileImage(String imageUrl);

    void setTweetCreationDate(String creationDate);

    void setTweetText(String text);

    void setRetweetCount(int count);

    void finish();

}
