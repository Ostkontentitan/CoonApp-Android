package com.jodelapp.features.photos.usecases

import com.jodelapp.data.models.Photo
import com.jodelapp.features.photos.models.PhotoPresentationModel
import io.reactivex.Single

/**
 * Created by ottek on 20.09.2017.
 */
interface GetPhotosByAlbum {
    fun call(albumId: Int) : Single<List<PhotoPresentationModel>>
}