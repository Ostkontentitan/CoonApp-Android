package com.jodelapp.features.photos.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.data.models.Album
import com.jodelapp.data.models.Photo
import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.photos.models.PhotoPresentationModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by ottek on 20.09.2017.
 */
class GetPhotosByAlbumTest {

    @Test
    fun testCall() {
        val photos = listOf(Photo(1,1,"photo1","url1", "thumbUrl1"), Photo(1,2,"photo2","url2", "thumbUrl2"))
        val apiService = Mockito.mock(ApiService::class.java)
        val testObserver = TestObserver<List<PhotoPresentationModel>>()

        val getPhotosByAlbum = GetPhotosByAlbumImpl(apiService)

        Mockito.`when`(apiService.getPhotos(1)).thenReturn(Observable.just(photos))

        getPhotosByAlbum.call(1).toObservable().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val presentationModels = testObserver.values().first()
        Assert.assertEquals("photo1", presentationModels.first().title)
        Assert.assertEquals("thumbUrl1", presentationModels.first().thumbUrl)
        Assert.assertEquals("photo2", presentationModels[1].title)
        Assert.assertEquals("thumbUrl2", presentationModels[1].thumbUrl)
    }
}