package com.vsa.tweetclock.domain.interactor;

import com.twitter.sdk.android.core.AppSession;
import com.vsa.tweetclock.domain.TweetTic;

import rx.Observable;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public interface TweetClockInteractor {

    Observable<AppSession> loginGuest();

    Observable<TweetTic> searchTimeTweet(AppSession session);

    int getTimeForNextTicInSeconds();

}
