package com.vsa.tweetclock.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
    @Bind(R.id.img_twitter_profile_image)
    ImageView mImageViewUserProfileImage;
    @Bind(R.id.txt_tweet_user)
    TextView mTextViewTweetUser;
    @Bind(R.id.txt_tweet_user_name)
    TextView mTextViewTweetUserName;
    @Bind(R.id.txt_tweet_creation_date)
    TextView mTextViewTweetCreationDate;
    @Bind(R.id.txt_tweet_text)
    TextView mTextViewTweetText;
    @Bind(R.id.txt_retweet_count)
    TextView mTextViewRetweetCount;

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

    @Override
    public void setUser(String user) {
        mTextViewTweetUser.setText(
                String.format(getString(R.string.tweet_clock_user_name), user));
    }

    @Override
    public void setUserName(String userName) {
        mTextViewTweetUserName.setText(userName);
    }

    @Override
    public void setUserProfileImage(String imageUrl) {
        Picasso.with(this).load(imageUrl).into(mImageViewUserProfileImage);
    }

    @Override
    public void setTweetCreationDate(String creationDate) {
        mTextViewTweetCreationDate.setText(creationDate);
    }

    @Override
    public void setTweetText(String text) {
        mTextViewTweetText.setText(text);
    }

    @Override
    public void setRetweetCount(int count) {
        mTextViewRetweetCount.setText(
                String.format(getString(R.string.tweet_clock_retweet_count), count));
    }

}
