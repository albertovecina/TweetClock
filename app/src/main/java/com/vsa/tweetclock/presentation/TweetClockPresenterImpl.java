package com.vsa.tweetclock.presentation;

import com.vsa.tweetclock.domain.TweetTic;
import com.vsa.tweetclock.domain.interactor.TweetClockInteractor;
import com.vsa.tweetclock.view.TweetClockView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockPresenterImpl implements TweetClockPresenter, Observer<TweetTic> {

    private SimpleDateFormat mTweetClockDateFormat = new SimpleDateFormat("h:mm a '-' dd MMM yyyy",
            Locale.US);

    private TweetClockInteractor mInteractor;
    private TweetClockView mView;

    public TweetClockPresenterImpl(TweetClockView tweetClockView, TweetClockInteractor tweetClockInteractor) {
        mView = tweetClockView;
        mInteractor = tweetClockInteractor;
    }

    @Override
    public void onCreate() {
        mView.showProgress();
        mInteractor.loginGuest()
                .flatMap(appSession ->
                        Observable.merge(
                                //INITIAL SEARCH
                                mInteractor.searchTimeTweet(appSession),
                                //SEARCH EACH MINUTE
                                Observable.interval(mInteractor.getTimeForNextTicInSeconds(), 60,
                                        TimeUnit.SECONDS)
                                        .flatMap(n -> mInteractor.searchTimeTweet(appSession)))

                )
                .subscribe(this);
    }

    @Override
    public void onDestroy() {
    }

    private void showTweetTic(TweetTic tweetTic) {
        mView.hideProgress();
        mView.setUserProfileImage(tweetTic.getProfileImageUrl());
        mView.setUser(tweetTic.getUser());
        mView.setUserName(tweetTic.getUserName());
        mView.setTweetCreationDate(mTweetClockDateFormat.format(tweetTic.getCreationJavaDate()));
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
            showTweetTic(tweetTic);
    }
}
