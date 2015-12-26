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

    /**
     * Transform a {@link Tweet} into a {@link TweetTic}
     *
     * @param tweet Object to be transformed
     * @return Transformed {@link TweetTic} object
     */
    public static TweetTic transform(Tweet tweet) {
        TweetTic tweetTic = new TweetTic(tweet.id);
        tweetTic.setUser(tweet.user.screenName);
        tweetTic.setUserName(tweet.user.name);
        tweetTic.setProfileImageUrl(tweet.user.profileImageUrl);
        tweetTic.setCreationDate(tweet.createdAt);
        tweetTic.setText(tweet.text);
        tweetTic.setRetweetCount(tweet.retweetCount);
        return tweetTic;
    }

    /**
     * Transform a {@link Tweet} Collection into a {@link TweetTic} List
     *
     * @param tweets Collection to be transformed
     * @return Transformed {@link TweetTic} List
     */
    public static List<TweetTic> transform(Collection<Tweet> tweets) {
        List<TweetTic> tweetTicList = new ArrayList<>();
        for (Tweet tweet : tweets)
            tweetTicList.add(transform(tweet));
        return tweetTicList;
    }


}
