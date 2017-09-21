package com.jodelapp.views.activities


import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jodelapp.App
import com.jodelapp.AppModule
import com.jodelapp.DaggerMockAppComponent
import com.jodelapp.screen_robots.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val component = DaggerMockAppComponent.builder()
                .appModule(AppModule(app)).build()

        // TODO reset UserManager / clean app state

        app.appComponent = component

        mock {
            enqueueResponse("todosUserId1.json")
        }

        activityTestRule.launchActivity(null)
    }

    private val app: App
        get() = InstrumentationRegistry.getInstrumentation()
                .targetContext.applicationContext as App


    @Test
    fun userAlbumsAndPhotosCanBeAccessed() {
        mock {
            enqueueResponse("albumsUserId1.json")
            enqueueResponse("photosAlbumId1.json")
        } perform {
            navigationToPhotos()
            tapOnText("quidem molestiae enim")
        } assume {
            textPresent("accusamus beatae ad facilis cum similique qui sunt")
        }
    }


    @Test
    fun changingUserUpdatesProfile() {
        mock {
            enqueueResponse("users.json")
        } perform {
            navigationToProfile()
            tapOnText("Ervin Howell")
        } assume {
            textPresent("Shanna@melissa.tv\n010-692-6593 x09125")
        }
    }

    @Test
    fun changingUserUpdatesTodos() {
        mock {
            enqueueResponse("users.json")
            enqueueResponse("todosUserId3.json")
        } perform {
            navigationToProfile()
            tapOnText("Clementine Bauch")
            navigationToTodos()
        } assume {
            textPresent("aliquid amet impedit consequatur aspernatur placeat eaque fugiat suscipit")
        }
    }
}











