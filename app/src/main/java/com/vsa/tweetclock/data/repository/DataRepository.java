package com.vsa.tweetclock.data.repository;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.TwitterCore;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class DataRepository {

    private static DataRepository sRepository;

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (sRepository == null)
            sRepository = new DataRepository();
        return sRepository;
    }

    public void doGuestLogin(Callback<AppSession> callback) {
        TwitterCore.getInstance().logInGuest(callback);
    }

}
