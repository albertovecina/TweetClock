package com.vsa.tweetclock.domain.interactor;

import com.twitter.sdk.android.core.AppSession;
import com.vsa.tweetclock.domain.TweetTic;
import com.vsa.tweetclock.domain.repository.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Observable;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class TweetClockInteractorImpl implements TweetClockInteractor {

    private static final String QUERY_TEXT_PATTERN_IT_IS = "\"It is %s and\"";
    private static final String QUERY_TEXT_PATTERN_ITS = "\"It's %s and\"";
    private static final String QUERY_TEXT_PATTERN_NON_EXACT = "It's \"%s\" and";
    private static final String TIME_PATTERN_24_H = "H:mm";
    private static final String TIME_PATTERN_24_HH = "HH:mm";
    private static final String TIME_PATTERN_24_H_A = "H:mm a";
    private static final String TIME_PATTERN_24_HH_A = "HH:mm a";
    private static final String TIME_PATTERN_12_H = "h:mm";
    private static final String TIME_PATTERN_12_HH = "hh:mm";
    private static final String TIME_PATTERN_12_H_A = "h:mm a";
    private static final String TIME_PATTERN_12_HH_A = "hh:mm a";
    private static final String[] TIME_PATTERNS = new String[]{
            TIME_PATTERN_24_H, TIME_PATTERN_24_HH,
            TIME_PATTERN_24_H_A, TIME_PATTERN_24_HH_A,
            TIME_PATTERN_12_H, TIME_PATTERN_12_HH,
            TIME_PATTERN_12_H_A, TIME_PATTERN_12_HH_A};

    private SimpleDateFormat mDateFormat = new SimpleDateFormat(TIME_PATTERN_24_HH, Locale.US);
    private Repository mRepository;

    public TweetClockInteractorImpl(Repository repository) {
        mRepository = repository;
    }

    /**
     * Request the guest session using the Twitter API
     *
     * @return An Observable that emits a {@link AppSession} object
     */
    @Override
    public Observable<AppSession> loginGuest() {
        return mRepository.doGuestLogin();
    }

    /**
     * Search a tweet with that matches the predefined message formats
     *
     * @param session {@link AppSession} Object that represets the Twitter guest session
     * @return An Observable that emits a {@link TweetTic}
     */
    @Override
    public Observable<TweetTic> searchTimeTweet(AppSession session) {
        Date currentDate = mRepository.getCurrentDate();
        String queryItIs = buildQuery(currentDate,
                new String[]{QUERY_TEXT_PATTERN_IT_IS},
                TIME_PATTERNS);
        String queryIts = buildQuery(currentDate,
                new String[]{QUERY_TEXT_PATTERN_ITS},
                TIME_PATTERNS);
        String queryNonExact = buildQuery(currentDate,
                new String[]{QUERY_TEXT_PATTERN_NON_EXACT},
                new String[]{TIME_PATTERN_12_H}); //QUERY FOR NON-EXACT PHRASE
        return Observable.combineLatest(
                Observable.zip( // LAUNCH TWO REQUESTS TO AVOID THE QUERY LENGTH LIMITATION
                        mRepository.searchTweet(session, queryItIs),
                        mRepository.searchTweet(session, queryIts),
                        (resultsA, resultsB) -> new ArrayList() {
                            {
                                addAll(resultsA);
                                addAll(resultsB);
                            }
                        }
                ),
                mRepository.searchTweet(session, queryNonExact), //LAUNCH NON-STRICT QUERY TO ENSURE ONE RESULT AT LEAST
                (this::pickTweet));
    }

    /**
     * Provides the time remaining for the next minute in seconds
     * @return
     */
    @Override
    public int getTimeForNextTicInSeconds() {
        int seconds = Calendar.getInstance().get(Calendar.SECOND);
        return 60 - seconds;
    }

    private String buildQuery(Date currentDate, String[] messageFormats, String[] timePatterns) {
        final String or = " OR ";
        StringBuilder stringBuilderQuery = new StringBuilder();
        for (String messageFormat : messageFormats) {
            for (String timePattern : timePatterns) {
                mDateFormat.applyPattern(timePattern);
                String query = String.format(messageFormat, mDateFormat.format(currentDate));
                stringBuilderQuery.append(query);
                stringBuilderQuery.append(or);
            }
        }
        return stringBuilderQuery.substring(0, stringBuilderQuery.length() - or.length());
    }

    /**
     * Picks the best choice from the results. Most accuracy first, most retwitted first, recent first
     *
     * @param bestCaseResults       {@link TweetTic} List that contains the results of the distinct accepted text formats
     * @param nonStrictQueryResults {@link TweetTic} List that contains the results for an alternative query with a non-strict format
     * @return
     */
    private TweetTic pickTweet(List<TweetTic> bestCaseResults, List<TweetTic> nonStrictQueryResults) {
        List<TweetTic> results;
        if (!bestCaseResults.isEmpty())
            results = bestCaseResults;
        else
            results = nonStrictQueryResults;
        Collections.sort(results, (lhs, rhs) -> {
            int ret = rhs.getRetweetCount() - lhs.getRetweetCount();
            if (ret == 0)
                return rhs.getCreationJavaDate().compareTo(lhs.getCreationJavaDate());
            return ret;
        });
        if (results.size() > 0)
            return results.get(0);
        else
            return null;
    }

}
