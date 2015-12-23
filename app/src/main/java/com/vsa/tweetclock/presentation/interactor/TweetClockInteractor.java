package com.vsa.tweetclock.presentation.interactor;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.models.Search;
import com.vsa.tweetclock.domain.TweetTic;

import java.sql.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public interface TweetClockInteractor {

    Observable<AppSession> loginGuest();

    Observable<List<TweetTic>> searchTweet(AppSession session, Date date);

}
