package com.jodelapp.views.activities

import com.jodelapp.R
import com.jodelapp.utilities.rx.RxDisposableFactory
import com.jodelapp.utilities.rx.RxDisposables
import com.jodelapp.utilities.rx.ThreadTransformer
import javax.inject.Inject

class MainActivityPresenter @Inject
constructor(private val view: MainActivityContract.View,
            threadTransformer: ThreadTransformer,
            rxDisposableFactory: RxDisposableFactory) : MainActivityContract.Presenter {

    private val disposables: RxDisposables = rxDisposableFactory.get()

    override fun onCreate(hasSavedInstanceState: Boolean) {

        // avoid navigating to default when rotation happened.
        if(!hasSavedInstanceState) {
            view.loadToDoPage()
            view.setBottomNavigationActiveOnToDo();
        }
    }

    override fun onDestroy() {
        disposables.clear()
    }

    override fun onNavigationItemSelected(itemId: Int) {
        when(itemId) {
            R.id.bn_photos -> view.replaceWithPhotosPage()
            R.id.bn_profile -> view.replaceWithProfilePage()
            R.id.bn_tasks -> view.replaceWithToDoPage()
        }
    }
}
