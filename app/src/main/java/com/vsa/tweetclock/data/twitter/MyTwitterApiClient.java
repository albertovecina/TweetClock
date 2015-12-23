package com.vsa.tweetclock.data.twitter;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by albertovecinasanchez on 23/12/15.
 */
public class MyTwitterApiClient extends TwitterApiClient {
    public MyTwitterApiClient(AppSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public CustomService getCustomService() {
        return getService(CustomService.class);
    }

    // example users/show service endpoint
    interface CustomService {
        @GET("/1.1/users/show.json")
        void show(@Query("user_id") long id, Callback<User> cb);
    }
}



