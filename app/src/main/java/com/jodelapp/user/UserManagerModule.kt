package com.jodelapp.user

import dagger.Module
import dagger.Provides

/**
 * Created by ottek on 20.09.2017.
 */
@Module
class UserManagerModule {

    @Provides
    fun provideUserManager(impl: UserManagerImpl) : UserManager {
        return impl
    }
}