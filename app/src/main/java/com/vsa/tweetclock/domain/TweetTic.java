package com.vsa.tweetclock.domain;

/**
 * Created by albertovecinasanchez on 23/12/15.
 */
public class TweetTic {

    private long id;
    private String userName;
    private String profileImageUrl;
    private String text;
    private int retweetCount;

    public TweetTic(long id) {
        this.id = id;
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
