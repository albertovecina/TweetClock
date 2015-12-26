package com.vsa.tweetclock.modules;

import com.vsa.tweetclock.domain.interactor.TweetClockInteractor;
import com.vsa.tweetclock.domain.interactor.TweetClockInteractorImpl;
import com.vsa.tweetclock.domain.repository.Repository;
import com.vsa.tweetclock.presentation.TweetClockPresenter;
import com.vsa.tweetclock.presentation.TweetClockPresenterImpl;
import com.vsa.tweetclock.view.TweetClockView;
import com.vsa.tweetclock.view.activity.TweetClockActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by albertovecinasanchez on 26/12/15.
 */
@Module(
        injects = TweetClockActivity.class,
        includes = TweetClockRepositoryModule.class
)
public class TweetClockPresenterModule {

    public TweetClockView mView;

    public TweetClockPresenterModule(TweetClockView tweetClockView) {
        mView = tweetClockView;
    }

    @Provides
    @Singleton
    public TweetClockView provideTweetClockView() {
        return mView;
    }

    @Provides
    TweetClockInteractor providesTweetClockInteractor(Repository repository) {
        return new TweetClockInteractorImpl(repository);
    }

    @Provides
    public TweetClockPresenter provideTweetClockPresenter(TweetClockView view, TweetClockInteractor interactor) {
        return new TweetClockPresenterImpl(view, interactor);
    }
}
