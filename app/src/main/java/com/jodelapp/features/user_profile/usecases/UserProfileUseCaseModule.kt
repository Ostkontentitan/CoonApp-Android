package com.jodelapp.features.user_profile.usecases

import dagger.Module
import dagger.Provides

/**
 * Created by ottek on 19.09.2017.
 */
@Module
class UserProfileUseCaseModule {

    @Provides
    fun provideGetUserProfiles(impl : GetUserProfilesImpl) : GetUserProfiles {
        return impl
    }
}