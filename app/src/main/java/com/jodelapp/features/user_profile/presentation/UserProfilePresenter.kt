package com.jodelapp.features.user_profile.presentation

import android.util.Log
import com.jodelapp.user.model.User
import com.jodelapp.user.usecases.GetActiveUser
import com.jodelapp.user.usecases.SetActiveUser
import com.jodelapp.features.user_profile.models.UserPresentationModel
import com.jodelapp.features.user_profile.usecases.GetUserProfiles
import com.jodelapp.utilities.rx.RxDisposableFactory
import com.jodelapp.utilities.rx.RxDisposables
import com.jodelapp.utilities.rx.ThreadTransformer
import io.reactivex.rxkotlin.Singles
import javax.inject.Inject

class UserProfilePresenter
@Inject constructor(private val view: UserProfileContract.View,
                    val getActiveUser: GetActiveUser,
                    val setActiveUser: SetActiveUser,
                    val threadTransformer: ThreadTransformer,
                    factory: RxDisposableFactory,
                    val getUserProfiles: GetUserProfiles) : UserProfileContract.Presenter {

    val disposables : RxDisposables

    init {
        disposables = factory.get()
    }

    override fun onAttached() {
        disposables.add(Singles.zip(getUserProfiles.call(), getActiveUser.call(), { profiles, user -> Pair(profiles, user) })
                .compose<Pair<List<UserPresentationModel>, User>>(threadTransformer.applySchedulers<Pair<List<UserPresentationModel>, User>>())
                .subscribe(
                        { (userProfiles, activeUser) -> view.listUserProfiles(userProfiles)
                            val activeUserProfile = userProfiles.first { it.id == activeUser.id }
                            view.showDetailsForUserProfile(activeUserProfile)
                        }
                ) { error -> Log.e("UserProfiles", error.message) })
    }

    override fun onDetached() {
        disposables.clear()
    }

    override fun onUserProfileClicked(userProfileModel: UserPresentationModel) {
        disposables.add(setActiveUser.call(User(userProfileModel.id))
                .compose<Unit>(threadTransformer.applySchedulers<Unit>())
                .subscribe(
                        {
                            view.showDetailsForUserProfile(userProfileModel)
                        }
                ) { error -> Log.e("ChangeActiveUserProfile", error.message) })
    }
}