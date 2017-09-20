package com.jodelapp.user.usecases

import com.jodelapp.user.UserManager
import com.jodelapp.user.model.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 19.09.2017.
 */
class GetActiveUserImpl @Inject constructor(val userManager: UserManager) : GetActiveUser {

    override fun call(): Single<User> {
        return userManager.getActiveUser()
    }
}