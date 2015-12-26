package com.vsa.tweetclock.data.repository;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.vsa.tweetclock.data.mapper.TweetDataMapper;
import com.vsa.tweetclock.data.twitter.TweetClockApiClient;
import com.vsa.tweetclock.domain.TweetTic;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class DataRepository {

    private static DataRepository sRepository;

    private TweetDataMapper mTweetDataMapper = new TweetDataMapper();

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (sRepository == null)
            sRepository = new DataRepository();
        return sRepository;
    }

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

    public Observable<List<TweetTic>> searchTweet(AppSession session, String query) {
        TweetClockApiClient twitterApiClient = new TweetClockApiClient(session);
        return twitterApiClient.getCustomService().tweets(query,
                null, null, null, null, 50, null, null, null, true)
                .map(search -> mTweetDataMapper.transform(search.tweets))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Date getCurrentDate() {
        return new Date();
    }

}
