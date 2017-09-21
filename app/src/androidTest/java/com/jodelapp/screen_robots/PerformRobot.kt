package com.jodelapp.screen_robots

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import com.jodelapp.R

/**
 * Created by ottek on 21.09.2017.
 */
class PerformRobot : ScreenRobot {
    fun navigationToTodos() {
        waitFor(ViewMatchers.withId(R.id.bn_tasks))
        Espresso.onView(ViewMatchers.withId(R.id.bn_tasks)).perform(ViewActions.click())
    }
    fun navigationToPhotos() {
        waitFor(ViewMatchers.withId(R.id.bn_photos))
        Espresso.onView(ViewMatchers.withId(R.id.bn_photos)).perform(ViewActions.click())
    }
    fun navigationToProfile() {
        waitFor(ViewMatchers.withId(R.id.bn_profile))
        Espresso.onView(ViewMatchers.withId(R.id.bn_profile)).perform(ViewActions.click())
    }
    fun tapOnText(text: String) {
        waitFor(ViewMatchers.withText(text))
        Espresso.onView(ViewMatchers.withText(text)).perform(ViewActions.click())
    }

    infix fun assume(func: AssumeRobot.() -> Unit): AssumeRobot {
        return AssumeRobot().apply(func)
    }
}

fun perform(func: PerformRobot.() -> Unit) = PerformRobot().apply(func)