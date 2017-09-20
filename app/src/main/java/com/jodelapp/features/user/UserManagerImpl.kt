package com.jodelapp.features.user

import com.jodelapp.features.user.model.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ottek on 20.09.2017.
 */
class UserManagerImpl @Inject constructor() : UserManager {

    companion object {
        val lock = Any()
        var activeUser: User = User(1)
    }

    override fun getActiveUser(): Single<User> {
        return Single.defer({
            synchronized(lock, {
                Single.just(activeUser)
            })
        })
    }

    override fun setActiveUser(user: User): Single<Unit> {
        return Single.defer({
            synchronized(lock, {
                activeUser = user
                Single.just(Unit)
            })
        })
    }
}