package com.vsa.tweetclock.domain.repository;

import com.twitter.sdk.android.core.AppSession;
import com.vsa.tweetclock.domain.TweetTic;

import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by albertovecinasanchez on 27/12/15.
 */
public interface Repository {

    Observable<AppSession> doGuestLogin();

    Observable<List<TweetTic>> searchTweet(AppSession session, String query);

    Date getCurrentDate();

}
