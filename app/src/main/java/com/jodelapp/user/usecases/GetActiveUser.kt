package com.jodelapp.user.usecases

import com.jodelapp.user.model.User
import io.reactivex.Single

/**
 * Created by ottek on 19.09.2017.
 */
interface GetActiveUser {
    fun call() : Single<User>
}