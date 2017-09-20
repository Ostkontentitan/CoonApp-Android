package com.jodelapp.features.photos.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.data.models.Album
import com.jodelapp.features.photos.models.AlbumPresentationModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenCalled
import org.junit.Assert.assertEquals

/**
 * Created by ottek on 20.09.2017.
 */
class GetAlbumsByUserTest {

    @Test
    fun testCall() {
        val albums = listOf(Album(1, 1, "uno"), Album(1, 2, "dos"))
        val apiService = mock(ApiService::class.java)
        val testObserver = TestObserver<List<AlbumPresentationModel>>()

        val getAlbumsByUser = GetAlbumsByUserImpl(apiService)


        whenCalled(apiService.getAlbums(1)).thenReturn(Observable.just(albums))

        getAlbumsByUser.call(1).toObservable().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val presentationModels = testObserver.values().first()
        assertEquals("uno", presentationModels.first().title)
        assertEquals("dos", presentationModels[1].title)
    }
}