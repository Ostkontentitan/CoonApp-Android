package com.jodelapp.features.user.usecases

import dagger.Module
import dagger.Provides

/**
 * Created by ottek on 19.09.2017.
 */
@Module
class UserUseCaseModule {

    @Provides
    fun provideGetActiveUser(impl : GetActiveUserImpl) : GetActiveUser {
        return impl
    }
}