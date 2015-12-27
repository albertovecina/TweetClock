package com.vsa.tweetclock.domain;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by albertovecinasanchez on 23/12/15.
 */
public class TweetTic {

    private static final long EMPTY_TWEET_ID = -1;
    private static final String EMPTY_TWEET_TEXT = "";

    private static final String TWITTER_DATE_PATTERN = "EEE MMM dd HH:mm:ss Z yyyy"; //Fri Dec 25 17:05:36 +0000 2015
    private static final SimpleDateFormat TWITTER_DATE_FORMATTER = new SimpleDateFormat(TWITTER_DATE_PATTERN, Locale.US);

    private long id;
    private String user;
    private String userName;
    private String profileImageUrl;
    private String creationDate;
    private String text;
    private int retweetCount;

    public TweetTic() {
        id = EMPTY_TWEET_ID;
        text = EMPTY_TWEET_TEXT;
    }

    public TweetTic(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Date getCreationJavaDate() {
        try {
            return TWITTER_DATE_FORMATTER.parse(creationDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

}
