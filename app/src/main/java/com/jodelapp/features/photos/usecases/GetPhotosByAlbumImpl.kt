package com.jodelapp.features.photos.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.photos.models.PhotoPresentationModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 20.09.2017.
 */
class GetPhotosByAlbumImpl @Inject constructor(val apiService: ApiService)  : GetPhotosByAlbum {
    override fun call(albumId: Int): Single<List<PhotoPresentationModel>> {
        return apiService.getPhotos(albumId)
                .flatMapIterable { photos -> photos }
                .map { photo -> PhotoPresentationModel(photo.title, photo.thumbnailUrl) }
                .toList()
    }
}