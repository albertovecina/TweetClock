package com.vsa.tweetclock.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vsa.tweetclock.view.application.TweetClockApplication;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by albertovecinasanchez on 26/12/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((TweetClockApplication) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();
}
