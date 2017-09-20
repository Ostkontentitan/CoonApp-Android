package com.jodelapp.features.user

import com.jodelapp.features.user.model.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.Observables

/**
 * Created by ottek on 20.09.2017.
 */
interface UserManager {
    fun getActiveUser(): Single<User>
    fun setActiveUser(user: User): Single<Unit>
}