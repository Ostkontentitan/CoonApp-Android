package com.jodelapp.features.photos.presentation

import android.util.Log
import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.photos.models.PhotoPresentationModel
import com.jodelapp.features.photos.usecases.GetAlbumsByUser
import com.jodelapp.features.photos.usecases.GetPhotosByAlbum
import com.jodelapp.features.user.usecases.GetActiveUser
import com.jodelapp.utilities.rx.RxDisposableFactory
import com.jodelapp.utilities.rx.RxDisposables
import com.jodelapp.utilities.rx.ThreadTransformer
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserPhotoListPresenter
@Inject constructor(private val view: UserPhotoListContract.View,
                    val getActiveUser: GetActiveUser,
                    val threadTransformer: ThreadTransformer,
                    factory: RxDisposableFactory,
                    val getAlbumsByUser: GetAlbumsByUser,
                    val getPhotosByAlbum: GetPhotosByAlbum) : UserPhotoListContract.Presenter {

    val disposables : RxDisposables

    init {
        disposables = factory.get()
    }

    override fun onAttached() {
        disposables.add(getActiveUser.call().flatMap({ (id) -> getAlbumsByUser.call(id) })
                .compose<List<AlbumPresentationModel>>(threadTransformer.applySchedulers<List<AlbumPresentationModel>>())
                .subscribe(
                        { albums -> view.loadAlbumsList(albums) }
                ) { error -> Log.e("UserAlbum", error.message) })
    }

    override fun onDetached() {
        disposables.clear()
    }

    override fun onAlbumClicked(album: AlbumPresentationModel) {
        disposables.add(getPhotosByAlbum.call(album.id)
                .compose<List<PhotoPresentationModel>>(threadTransformer.applySchedulers<List<PhotoPresentationModel>>())
                .subscribe(
                        { photos -> view.loadPhotosGrid(photos) }
                ) { error -> Log.e("AlbumPhoto", error.message) })
    }
}
