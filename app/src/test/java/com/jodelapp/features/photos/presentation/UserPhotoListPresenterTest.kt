package com.jodelapp.features.photos.presentation

import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.photos.models.PhotoPresentationModel
import com.jodelapp.features.photos.usecases.GetAlbumsByUser
import com.jodelapp.features.photos.usecases.GetPhotosByAlbum
import com.jodelapp.user.model.User
import com.jodelapp.user.usecases.GetActiveUser
import com.jodelapp.utilities.rx.RxDisposableFactory
import com.jodelapp.utilities.rx.RxDisposables
import com.jodelapp.utilities.rx.ThreadTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenCalled

/**
 * Created by ottek on 20.09.2017.
 */
class UserPhotoListPresenterTest {

    lateinit var view: UserPhotoListContract.View
    lateinit var getActiveUser: GetActiveUser
    lateinit var threadTransformer: ThreadTransformer
    lateinit var disposableFactory: RxDisposableFactory
    lateinit var getAlbumsByUser: GetAlbumsByUser
    lateinit var getPhotosByAlbum: GetPhotosByAlbum

    lateinit var disposableMock: RxDisposables

    lateinit var presenter: UserPhotoListContract.Presenter

    @Before
    fun init() {
        view = mock(UserPhotoListContract.View::class.java)
        getActiveUser = mock(GetActiveUser::class.java)
        threadTransformer = mock(ThreadTransformer::class.java)
        disposableFactory = mock(RxDisposableFactory::class.java)
        getAlbumsByUser = mock(GetAlbumsByUser::class.java)
        getPhotosByAlbum = mock(GetPhotosByAlbum::class.java)

        disposableMock = mock(RxDisposables::class.java)
        whenCalled(disposableFactory.get()).thenReturn(disposableMock)

        presenter = UserPhotoListPresenter(view, getActiveUser, threadTransformer, disposableFactory, getAlbumsByUser, getPhotosByAlbum)
    }

    @Test
    fun testInit_factoryIsAskedForDisposables() {
        verify(disposableFactory).get()
    }

    @Test
    fun testOnAttached_errorOccurs_noViewAction() {
        val albums: Single<List<AlbumPresentationModel>> = Single.error(ArrayIndexOutOfBoundsException())
        whenCalled(getActiveUser.call()).thenReturn(Single.just(User(1)))
        whenCalled(threadTransformer.applySchedulers<List<AlbumPresentationModel>>()).thenReturn(SingleTransformer {  it })
        whenCalled(getAlbumsByUser.call(1)).thenReturn(albums)
        presenter.onAttached()

        verify(view, times(0)).loadAlbumsList(any())
    }

    @Test
    fun testOnAttached_albumsLoaded_viewActionTriggered() {
        val albums = listOf(AlbumPresentationModel(1, "Holidays 2016"))
        val albumsSingle: Single<List<AlbumPresentationModel>> = Single.just(albums)
        whenCalled(getActiveUser.call()).thenReturn(Single.just(User(1)))
        whenCalled(threadTransformer.applySchedulers<List<AlbumPresentationModel>>()).thenReturn(SingleTransformer {  it })
        whenCalled(getAlbumsByUser.call(1)).thenReturn(albumsSingle)
        presenter.onAttached()

        verify(view, times(1)).loadAlbumsList(albums)
    }

    @Test
    fun testOnDetached_disposablesCleared() {
        presenter.onDetached()

        verify(disposableMock, times(1)).clear()
    }

    @Test
    fun testOnAlbumClicked_photosLoaded_viewActionTriggered() {
        val photos = listOf(PhotoPresentationModel("abc", "http://url.to.img.png"))
        val clickedAlbum = AlbumPresentationModel(1, "Holidays 2016")
        whenCalled(threadTransformer.applySchedulers<List<AlbumPresentationModel>>()).thenReturn(SingleTransformer {  it })
        whenCalled(getPhotosByAlbum.call(1)).thenReturn(Single.just(photos))
        presenter.onAlbumClicked(clickedAlbum)

        verify(view, times(1)).loadPhotosGrid(photos)
    }

    @Test
    fun testOnAlbumClicked_errorOccurs_noViewAction() {
        val clickedAlbum = AlbumPresentationModel(1, "Holidays 2016")
        whenCalled(threadTransformer.applySchedulers<List<AlbumPresentationModel>>()).thenReturn(SingleTransformer {  it })
        whenCalled(getPhotosByAlbum.call(1)).thenReturn(Single.error(StackOverflowError()))
        presenter.onAlbumClicked(clickedAlbum)

        verify(view, times(0)).loadPhotosGrid(any())
    }
}