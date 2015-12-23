package com.vsa.tweetclock.presentation.interactor;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.vsa.tweetclock.data.repository.DataRepository;
import com.vsa.tweetclock.domain.TweetTic;
import com.vsa.tweetclock.presentation.event.BUS;
import com.vsa.tweetclock.presentation.event.login.EventOnGuestLoginError;
import com.vsa.tweetclock.presentation.event.login.EventOnGuestLoginSuccess;

import java.sql.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockInteractorImpl implements TweetClockInteractor {

    DataRepository mRepository = DataRepository.getInstance();

    @Override
    public Observable<AppSession> loginGuest() {
        return mRepository.doGuestLogin();
    }

    @Override
    public Observable<List<TweetTic>> searchTweet(AppSession session, Date date) {
        final String searchString = "It is \"19:40\" and";
        return mRepository.searchTweet(session, searchString);
    }

}
