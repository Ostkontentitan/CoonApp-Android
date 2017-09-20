package com.jodelapp.features.photos.usecases


import dagger.Module
import dagger.Provides

@Module
class UserPhotoListUseCaseModule{
    @Provides
    internal fun provideGetAlbumsByUser(impl: GetAlbumsByUserImpl): GetAlbumsByUser {
        return impl
    }
    @Provides
    internal fun provideGetPhotosByAlbum(impl: GetPhotosByAlbumImpl): GetPhotosByAlbum {
        return impl
    }
}
