package com.vsa.tweetclock.presentation.interactor;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.GuestCallback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.vsa.tweetclock.presentation.event.BUS;
import com.vsa.tweetclock.presentation.event.login.EventOnGuestLoginError;
import com.vsa.tweetclock.presentation.event.login.EventOnGuestLoginSuccess;
import com.vsa.tweetclock.presentation.event.search.EventTweetSearchError;
import com.vsa.tweetclock.presentation.event.search.EventTweetSearchSuccess;
import com.vsa.tweetclock.data.repository.DataRepository;

import java.sql.Date;
import java.util.List;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockInteractorImpl implements TweetClockInteractor {

    DataRepository mRepository = DataRepository.getInstance();

    @Override
    public void loginGuest() {
        mRepository.doGuestLogin(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> result) {
                BUS.getInstance().post(new EventOnGuestLoginSuccess(result.data));
            }

            @Override
            public void failure(TwitterException e) {
                BUS.getInstance().post(new EventOnGuestLoginError());
            }
        });
    }

    @Override
    public void searchTweet(AppSession session, Date date) {
        final String searchString = "It is \"19:40\" and";
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
        twitterApiClient.getSearchService().tweets(searchString,
                null, null, null, null, 50, null, null, null, true, new GuestCallback<>(new Callback<Search>() {
                    @Override
                    public void success(Result<Search> result) {
                        List<Tweet> tweetList = result.data.tweets;
                        if (tweetList.size() > 0)
                            BUS.getInstance().post(new EventTweetSearchSuccess(tweetList.get(0)));
                        else
                            BUS.getInstance().post(new EventTweetSearchError());

                    }

                    @Override
                    public void failure(TwitterException exception) {
                        BUS.getInstance().post(new EventTweetSearchError());
                    }
                }));
    }

}
