package com.vsa.tweetclock.presentation;

import android.util.Log;

import com.twitter.sdk.android.core.AppSession;
import com.vsa.tweetclock.domain.TweetTic;
import com.vsa.tweetclock.presentation.event.BUS;
import com.vsa.tweetclock.presentation.interactor.TweetClockInteractor;
import com.vsa.tweetclock.presentation.interactor.TweetClockInteractorImpl;
import com.vsa.tweetclock.view.TweetClockView;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockPresenterImpl implements TweetClockPresenter {

    private AppSession mGuestSession;
    private TweetClockInteractor mInteractor = new TweetClockInteractorImpl();
    private TweetClockView mView;

    public TweetClockPresenterImpl(TweetClockView tweetClockView) {
        mView = tweetClockView;
    }

    @Override
    public void onCreate() {
        mInteractor.loginGuest()
                .flatMap(appSession -> mInteractor.searchTweet((mGuestSession = appSession), null))
                .subscribe(tweetTics -> {
                    setTweetTic(tweetTics.get(0));
                    for (TweetTic tweetTic : tweetTics)
                        Log.d("PRUEBA", tweetTic.getText());
                    Log.d("PRUEBA", "SESSION NULL = " + (mGuestSession == null));
                });
    }

    @Override
    public void onDestroy() {
        BUS.getInstance().unregister(this);
    }

    private void setTweetTic(TweetTic tweetTic) {
        mView.setUserProfileImage(tweetTic.getProfileImageUrl());
        mView.setUser(tweetTic.getUser());
        mView.setUserName(tweetTic.getUserName());
        mView.setTweetCreationDate(tweetTic.getCreationDate());
        mView.setTweetText(tweetTic.getText());
        mView.setRetweetCount(tweetTic.getRetweetCount());
    }

}
