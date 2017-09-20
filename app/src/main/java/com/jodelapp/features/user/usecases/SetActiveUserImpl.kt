package com.jodelapp.features.user.usecases

import com.jodelapp.features.user.UserManager
import com.jodelapp.features.user.model.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 20.09.2017.
 */
class SetActiveUserImpl @Inject constructor(val userManager: UserManager) : SetActiveUser {
    override fun call(user: User): Single<Unit> {
        return userManager.setActiveUser(user)
    }
}