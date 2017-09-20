package com.jodelapp.features.user.usecases

import com.jodelapp.features.user.UserManager
import com.jodelapp.features.user.UserManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ottek on 19.09.2017.
 */
@Module
class UserUseCaseModule {

    @Provides
    fun provideUserManager(impl: UserManagerImpl) : UserManager {
        return impl
    }

    @Provides
    fun provideGetActiveUser(impl : GetActiveUserImpl) : GetActiveUser {
        return impl
    }

    @Provides
    fun provideSetActiveUser(impl : SetActiveUserImpl) : SetActiveUser {
        return impl
    }
}