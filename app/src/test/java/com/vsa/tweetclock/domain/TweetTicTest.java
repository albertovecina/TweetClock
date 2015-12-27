package com.vsa.tweetclock.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by albertovecinasanchez on 27/12/15.
 */
public class TweetTicTest {

    private TweetTic mTweetTic;

    @Before
    public void setUp() {
        mTweetTic = new TweetTic();
    }

    @Test
    public void testTweetTicEmptyConstructor() {
        assertThat(mTweetTic.getId(), is(-1L));
        assertThat(mTweetTic.getText(), is(""));
    }

}
