package com.jodelapp.features.photos.usecases

import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.todos.models.TodoPresentationModel
import io.reactivex.Single

/**
 * Created by ottek on 19.09.2017.
 */
interface GetAlbumsByUser {
    fun call(userId: Int): Single<List<AlbumPresentationModel>>
}