package com.jodelapp.features.user.usecases

import com.jodelapp.features.user.model.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 19.09.2017.
 */
class GetActiveUserImpl @Inject constructor() : GetActiveUser {

    companion object {
        val activeUser = User("1")
    }

    override fun call(): Single<User> {
        return Single.just(activeUser)
    }
}