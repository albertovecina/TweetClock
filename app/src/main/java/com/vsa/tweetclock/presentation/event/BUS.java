package com.vsa.tweetclock.presentation.event;

import com.squareup.otto.Bus;

/**
 * Created by albertovecinasanchez on 21/12/15.
 */
public class BUS {

    private static Bus sBus;

    public static Bus getInstance() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;
    }

}
