package com.vsa.tweetclock.presentation;

import android.util.Log;

import com.vsa.tweetclock.domain.TweetTic;
import com.vsa.tweetclock.domain.interactor.TweetClockInteractor;
import com.vsa.tweetclock.domain.interactor.TweetClockInteractorImpl;
import com.vsa.tweetclock.presentation.event.BUS;
import com.vsa.tweetclock.view.TweetClockView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockPresenterImpl implements TweetClockPresenter, Observer<TweetTic> {

    private TweetClockInteractor mInteractor;
    private TweetClockView mView;

    public TweetClockPresenterImpl(TweetClockView tweetClockView, TweetClockInteractor tweetClockInteractor) {
        mView = tweetClockView;
        mInteractor = tweetClockInteractor;
    }

    @Override
    public void onCreate() {
        mInteractor.loginGuest()
                .flatMap(appSession ->
                        Observable.merge(
                                mInteractor.searchTimeTweet(appSession),
                                Observable.interval(mInteractor.getTimeForNextTicInSeconds(), 60, TimeUnit.SECONDS)
                                        .flatMap(n -> mInteractor.searchTimeTweet(appSession)))

                )
                .subscribe(this);
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

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        mView.showNoTweetsError();
    }

    @Override
    public void onNext(TweetTic tweetTic) {
        if (tweetTic == null)
            mView.showNoTweetsError();
        else
            setTweetTic(tweetTic);
    }
}
