package com.jodelapp.features.photos.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.user.usecases.GetActiveUser
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 19.09.2017.
 */
class GetAlbumsByUserImpl @Inject constructor(val apiService: ApiService ) : GetAlbumsByUser {
    override fun call(userId: Int): Single<List<AlbumPresentationModel>> {
        return apiService.getAlbums(userId)
                .flatMapIterable { albums -> albums }
                .map { album -> AlbumPresentationModel(album.id, album.title) }
                .toList()
    }
}