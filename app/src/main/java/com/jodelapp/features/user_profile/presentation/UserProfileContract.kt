package com.jodelapp.features.user_profile.presentation

import com.jodelapp.features.user_profile.models.UserPresentationModel

/**
 * Created by ottek on 20.09.2017.
 */
interface UserProfileContract {
    interface View {
        fun listUserProfiles(userProfiles: List<UserPresentationModel>)
        fun showDetailsForUserProfile(activeUserProfile: UserPresentationModel)
    }
    interface Presenter {
        fun onAttached()
        fun onDetached()
        fun onUserProfileClicked(userProfileModel: UserPresentationModel)

    }
}