package com.jodelapp.features.user.usecases

import com.jodelapp.features.user.model.User
import io.reactivex.Single

/**
 * Created by ottek on 20.09.2017.
 */
interface SetActiveUser {
    fun call(user: User): Single<Unit>
}