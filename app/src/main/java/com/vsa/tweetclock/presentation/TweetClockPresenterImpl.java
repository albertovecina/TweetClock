package com.vsa.tweetclock.presentation;

import com.squareup.otto.Subscribe;
import com.twitter.sdk.android.core.AppSession;
import com.vsa.tweetclock.presentation.event.BUS;
import com.vsa.tweetclock.presentation.event.login.EventOnGuestLoginError;
import com.vsa.tweetclock.presentation.event.login.EventOnGuestLoginSuccess;
import com.vsa.tweetclock.presentation.event.search.EventTweetSearchSuccess;
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
        BUS.getInstance().register(this);
        mInteractor.loginGuest();
    }

    @Override
    public void onDestroy() {
        BUS.getInstance().unregister(this);
    }

    @Subscribe
    public void onGuestLoginSuccess(EventOnGuestLoginSuccess event) {
        //TODO: START SEARCHING TWEETS
        mGuestSession = event.getAppSession();
        mInteractor.searchTweet(mGuestSession, null);
    }

    @Subscribe
    public void onGuestLoginError(EventOnGuestLoginError event) {
        mView.showLoginError();
    }

    @Subscribe
    public void onTweetSearchSuccess(EventTweetSearchSuccess event) {
        mView.showTweet(event.getTweet());
    }

}
