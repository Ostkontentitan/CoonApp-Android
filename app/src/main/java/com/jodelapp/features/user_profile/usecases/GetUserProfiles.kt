package com.jodelapp.features.user_profile.usecases

import com.jodelapp.features.user_profile.models.UserPresentationModel
import io.reactivex.Single

/**
 * Created by ottek on 20.09.2017.
 */
interface GetUserProfiles {
    fun call(): Single<List<UserPresentationModel>>
}