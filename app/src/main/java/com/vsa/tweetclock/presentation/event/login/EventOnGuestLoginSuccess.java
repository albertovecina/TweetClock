package com.vsa.tweetclock.presentation.event.login;

import com.twitter.sdk.android.core.AppSession;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class EventOnGuestLoginSuccess {

    private AppSession appSession;

    public EventOnGuestLoginSuccess(AppSession appSession) {
        this.appSession = appSession;
    }

    public AppSession getAppSession() {
        return appSession;
    }
}
