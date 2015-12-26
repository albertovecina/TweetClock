package com.vsa.tweetclock.modules;

import com.vsa.tweetclock.data.repository.DataRepository;
import com.vsa.tweetclock.domain.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by albertovecinasanchez on 27/12/15.
 */
@Module(
        library = true
)
public class TweetClockRepositoryModule {

    @Provides
    @Singleton
    Repository providesRepository() {
        return new DataRepository();
    }

}
