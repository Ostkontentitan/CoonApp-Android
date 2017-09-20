package com.jodelapp.user

import com.jodelapp.user.model.User
import io.reactivex.Single

/**
 * Created by ottek on 20.09.2017.
 */
interface UserManager {
    fun getActiveUser(): Single<User>
    fun setActiveUser(user: User): Single<Unit>
}