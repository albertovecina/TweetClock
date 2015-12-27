package com.vsa.tweetclock.data.mapper;

import com.twitter.sdk.android.core.models.Tweet;
import com.vsa.tweetclock.domain.TweetTic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class TweetDataMapperTest {

    private static final int LIST_SIZE = 50;

    List<Tweet> mTweetList;

    @Before
    public void setUp() {
        mTweetList = new ArrayList<>();
        for (int x = 0; x < LIST_SIZE; x++)
            mTweetList.add(mock(Tweet.class));

    }

    @Test
    public void testTransformationIsCorrect() throws Exception {
        List<TweetTic> tweetTicList = TweetDataMapper.transform(mTweetList);
        assertThat(tweetTicList, is(not(nullValue())));
        assertThat(tweetTicList.size(), is(LIST_SIZE));
    }
}