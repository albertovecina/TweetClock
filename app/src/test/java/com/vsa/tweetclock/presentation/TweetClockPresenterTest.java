package com.vsa.tweetclock.presentation;

import com.vsa.tweetclock.domain.interactor.TweetClockInteractor;
import com.vsa.tweetclock.view.TweetClockView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by albertovecinasanchez on 27/12/15.
 */
public class TweetClockPresenterTest {

    @Mock
    private TweetClockView mMockView;
    @Mock
    private TweetClockInteractor mMockInteractor;
    private TweetClockPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mMockInteractor.loginGuest()).thenReturn(mock(Observable.class));
        mPresenter = new TweetClockPresenterImpl(mMockView, mMockInteractor);
    }

    @Test
    public void testLoginGuestIsCalledOnCreate() {
        mPresenter.onCreate();
        
        verify(mMockInteractor).loginGuest();
        verifyNoMoreInteractions(mMockInteractor);
    }

}
