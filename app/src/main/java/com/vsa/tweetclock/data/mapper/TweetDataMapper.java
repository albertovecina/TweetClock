package com.vsa.tweetclock.data.mapper;

import com.twitter.sdk.android.core.models.Tweet;
import com.vsa.tweetclock.domain.TweetTic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by albertovecinasanchez on 23/12/15.
 */
public class TweetDataMapper {

    public TweetTic transform(Tweet tweet) {
        TweetTic tweetTic = new TweetTic(tweet.id);
        tweetTic.setUserName(tweet.user.name);
        tweetTic.setProfileImageUrl(tweet.user.profileImageUrl);
        tweetTic.setText(tweet.text);
        tweetTic.setRetweetCount(tweet.retweetCount);
        return tweetTic;
    }

    public List<TweetTic> transform(Collection<Tweet> tweets) {
        List<TweetTic> tweetTicList = new ArrayList<>();
        for (Tweet tweet : tweets)
            tweetTicList.add(transform(tweet));
        return tweetTicList;
    }


}
