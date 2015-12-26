package com.vsa.tweetclock.domain.interactor;

import android.util.Log;

import com.twitter.sdk.android.core.AppSession;
import com.vsa.tweetclock.data.repository.DataRepository;
import com.vsa.tweetclock.domain.TweetTic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Observable;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockInteractorImpl implements TweetClockInteractor {

    private static final String TIME_PATTERN_24_H = "HH:mm";
    private static final String TIME_PATTERN_12_H = "hh:mm";
    private static final String TIME_PATTERN_12_H_A = "hh:mm a";

    private SimpleDateFormat mDateFormat = new SimpleDateFormat(TIME_PATTERN_24_H, Locale.US);
    private DataRepository mRepository = DataRepository.getInstance();

    @Override
    public Observable<AppSession> loginGuest() {
        return mRepository.doGuestLogin();
    }

    @Override
    public Observable<TweetTic> searchTimeTweet(AppSession session) {
        Date currentDate = mRepository.getCurrentDate();
        mDateFormat.applyPattern(TIME_PATTERN_24_H);
        String time24HFormat = mDateFormat.format(currentDate);
        mDateFormat.applyPattern(TIME_PATTERN_12_H);
        String time12HFormat = mDateFormat.format(currentDate);
        mDateFormat.applyPattern(TIME_PATTERN_12_H_A);
        String time12HFormatA = mDateFormat.format(currentDate);
        final String pattern1 = "\"It is " + time24HFormat + " and\"";
        final String pattern2 = "\"It is " + time12HFormat + " and\"";
        final String pattern3 = "\"It is " + time12HFormatA + " and\"";
        final String pattern4 = "\"It's " + time24HFormat + " and\"";
        final String pattern5 = "\"It's " + time12HFormat + " and\"";
        final String pattern6 = "\"It's " + time12HFormatA + " and\"";
        final String pattern7 = "It's \"" + time24HFormat + "\" and";
        return Observable.combineLatest(
                mRepository.searchTweet(session, pattern1),
                mRepository.searchTweet(session, pattern2),
                mRepository.searchTweet(session, pattern3),
                mRepository.searchTweet(session, pattern4),
                mRepository.searchTweet(session, pattern5),
                mRepository.searchTweet(session, pattern6),
                mRepository.searchTweet(session, pattern7),
                (this::manageResults));
    }

    private TweetTic manageResults(List<TweetTic> pattern1, List<TweetTic> pattern2,
                                   List<TweetTic> pattern3, List<TweetTic> pattern4,
                                   List<TweetTic> pattern5, List<TweetTic> pattern6,
                                   List<TweetTic> pattern7) {
        List<TweetTic> results = new ArrayList<>();
        results.addAll(pattern1);
        results.addAll(pattern2);
        results.addAll(pattern3);
        results.addAll(pattern4);
        results.addAll(pattern5);
        results.addAll(pattern6);
        if (results.isEmpty())
            results = pattern7;
        Collections.sort(results, (lhs, rhs) -> {
            int ret = rhs.getRetweetCount() - lhs.getRetweetCount();
            if (ret == 0)
                return lhs.getCreationJavaDate().compareTo(rhs.getCreationJavaDate());
            return ret;
        });
        for (TweetTic tt : results)
            Log.d("PRUEBA", "MESSAGE: " + tt.getText());
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

}
