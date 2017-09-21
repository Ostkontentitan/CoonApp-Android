package com.jodelapp.features.user_profile.usecases

import com.jodelapp.data.api.ApiService
import com.jodelapp.features.photos.models.PhotoPresentationModel
import com.jodelapp.features.user_profile.models.UserPresentationModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 20.09.2017.
 */
class GetUserProfilesImpl @Inject constructor(val apiService: ApiService) : GetUserProfiles {
    override fun call(): Single<List<UserPresentationModel>> {
        return apiService.userProfiles
                .flatMapIterable { profiles -> profiles }
                .map { profile -> UserPresentationModel(profile.id, profile.name,
                        "${profile.address.suite}\n${profile.address.street}\n${profile.address.city}",
                        "${profile.company.name}\n${profile.company.catchPhrase}\n${profile.company.bs}",
                        "${profile.email}\n${profile.phone}") }
                .toList()
    }
}