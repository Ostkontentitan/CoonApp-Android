package com.jodelapp.data.api

import com.jodelapp.data.models.Album
import com.jodelapp.data.models.Photo
import com.jodelapp.data.models.ToDo
import com.jodelapp.data.models.UserProfile

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/todos")
    fun getToDos(@Query("userId") userId: Int): Observable<List<ToDo>>

    @GET("/albums")
    fun getAlbums(@Query("userId") userId: Int): Observable<List<Album>>

    @GET("/photos")
    fun getPhotos(@Query("albumId") albumId: Int): Observable<List<Photo>>

    @get:GET("/users")
    val userProfiles: Observable<List<UserProfile>>

}
