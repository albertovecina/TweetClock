package com.vsa.tweetclock.presentation.interactor;

import com.twitter.sdk.android.core.AppSession;

import java.sql.Date;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public interface TweetClockInteractor {

    void loginGuest();

    void searchTweet(AppSession session, Date date);

}
