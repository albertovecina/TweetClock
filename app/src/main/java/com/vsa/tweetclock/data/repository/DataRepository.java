package com.vsa.tweetclock.data.repository;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.vsa.tweetclock.data.mapper.TweetDataMapper;
import com.vsa.tweetclock.data.twitter.TweetClockApiClient;
import com.vsa.tweetclock.domain.TweetTic;
import com.vsa.tweetclock.domain.repository.Repository;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class DataRepository implements Repository {

    @Override
    public Observable<AppSession> doGuestLogin() {
        return Observable.create(new Observable.OnSubscribe<AppSession>() {
            @Override
            public void call(Subscriber<? super AppSession> subscriber) {
                try {
                    TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
                        @Override
                        public void success(Result<AppSession> result) {
                            subscriber.onNext(result.data);
                            subscriber.onCompleted();
                        }

                        @Override
                        public void failure(TwitterException e) {
                            subscriber.onError(e);
                        }
                    });

                } catch (Exception e) {
                    subscriber.onError(e); // In case there are network errors
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<TweetTic>> searchTweet(AppSession session, String query) {
        TweetClockApiClient twitterApiClient = new TweetClockApiClient(session);
        return twitterApiClient.getCustomSearchService().tweets(query,
                null, null, null, null, 50, null, null, null, true)
                .map(search -> TweetDataMapper.transform(search.tweets))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Date getCurrentDate() {
        return new Date();
    }

}
