package com.jodelapp.screen_robots

import android.support.test.InstrumentationRegistry
import com.jodelapp.data.MockDataModule
import okhttp3.mockwebserver.MockResponse
import java.util.*

/**
 * Created by ottek on 21.09.2017.
 */
class MockRobot : ScreenRobot {
    fun enqueueResponse(file: String) = enqueueMockResponseJson(file)

    infix fun perform(func: PerformRobot.() -> Unit): PerformRobot {
        return PerformRobot().apply(func)
    }

    private fun enqueueMockResponseJson(file: String) =
            MockDataModule.mockWebServer.enqueue(MockResponse().setBody(readResource(file)))

    private fun readResource(file: String): String {
        val inputStream = InstrumentationRegistry.getContext().assets.open(file)
        val scanner = Scanner(inputStream)
        val builder = StringBuilder()
        do {
            builder.append(scanner.nextLine())
        } while (scanner.hasNextLine())
        return builder.toString()
    }
}

fun mock(func: MockRobot.() -> Unit) = MockRobot().apply(func)

