package com.vsa.tweetclock.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetView;
import com.vsa.tweetclock.R;
import com.vsa.tweetclock.presentation.TweetClockPresenter;
import com.vsa.tweetclock.presentation.TweetClockPresenterImpl;
import com.vsa.tweetclock.view.TweetClockView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetClockActivity extends AppCompatActivity implements TweetClockView {

    @Bind(R.id.wrapper_tweet)
    ViewGroup mWrapperTweet;

    private TweetClockPresenter mPresenter = new TweetClockPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showTweet(Tweet tweet) {
        mWrapperTweet.removeAllViews();
        TweetView tweetView = new TweetView(this, tweet);
        mWrapperTweet.addView(tweetView);
    }

    @Override
    public void showLoginError() {
        //TODO
    }
}
