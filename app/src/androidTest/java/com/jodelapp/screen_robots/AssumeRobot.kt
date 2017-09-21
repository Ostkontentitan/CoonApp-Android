package com.jodelapp.screen_robots

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers

/**
 * Created by ottek on 21.09.2017.
 */
class AssumeRobot : ScreenRobot {
    fun textPresent(title: String) {
        waitFor(ViewMatchers.withText(title))
        Espresso.onView(ViewMatchers.withText(title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}