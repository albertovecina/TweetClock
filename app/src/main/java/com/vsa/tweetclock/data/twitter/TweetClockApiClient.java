package com.vsa.tweetclock.data.twitter;

import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.services.params.Geocode;

import retrofit.http.EncodedQuery;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by albertovecinasanchez on 23/12/15.
 */
public class TweetClockApiClient extends TwitterApiClient {
    public TweetClockApiClient(AppSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public CustomSearchService getCustomSearchService() {
        return getService(CustomSearchService.class);
    }

    /**
     * Custom Twitter service that extends the search api to use Observables
     * {@link com.twitter.sdk.android.core.services.SearchService}
     */
    public interface CustomSearchService {
        @GET("/1.1/search/tweets.json")
        Observable<Search> tweets(@Query("q") String var1, @EncodedQuery("geocode") Geocode var2, @Query("lang") String var3, @Query("locale") String var4, @Query("result_type") String var5, @Query("count") Integer var6, @Query("until") String var7, @Query("since_id") Long var8, @Query("max_id") Long var9, @Query("include_entities") Boolean var10);
    }
}



