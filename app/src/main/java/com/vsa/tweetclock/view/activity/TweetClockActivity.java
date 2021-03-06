package com.vsa.tweetclock.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vsa.tweetclock.R;
import com.vsa.tweetclock.modules.TweetClockPresenterModule;
import com.vsa.tweetclock.presentation.TweetClockPresenter;
import com.vsa.tweetclock.view.TweetClockView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetClockActivity extends BaseActivity implements TweetClockView {

    @Bind(R.id.card_view_tweet)
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

    @Inject
    TweetClockPresenter mPresenter;

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
    protected List<Object> getModules() {
        return Arrays.asList(new TweetClockPresenterModule(this));
    }

    @Override
    public void showProgress() {
        mWrapperTweet.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        mWrapperTweet.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoTweetsError() {
        //TODO
        Toast.makeText(this, "There is no tweet", Toast.LENGTH_SHORT).show();
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
