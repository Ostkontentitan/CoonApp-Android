package com.jodelapp.screen_robots

import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import com.jodelapp.functions.waitForMatch
import org.hamcrest.Matcher

/**
 * Created by ottek on 21.09.2017.
 */
interface ScreenRobot {
    companion object {
        // I know IdlingResources but i don't like the idea of putting testcode into the app.
        // Curious about how you guys manage it!
        val PATIENCE = 2000L
    }

    fun waitFor(matcher: Matcher<View>) {
        Espresso.onView(ViewMatchers.isRoot()).perform(waitForMatch(matcher, PATIENCE))
    }
}