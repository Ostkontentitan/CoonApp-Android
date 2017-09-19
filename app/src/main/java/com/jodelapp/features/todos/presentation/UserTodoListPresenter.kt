package com.jodelapp.features.todos.presentation


import android.util.Log
import com.jodelapp.features.todos.models.TodoPresentationModel

import com.jodelapp.features.todos.usecases.GetTodoListByUser
import com.jodelapp.features.user.usecases.GetActiveUser
import com.jodelapp.utilities.rx.RxDisposableFactory
import com.jodelapp.utilities.rx.RxDisposables
import com.jodelapp.utilities.rx.ThreadTransformer

import javax.inject.Inject

class UserTodoListPresenter @Inject
constructor(private val view: UserTodoListContract.View,
            private val getActiveUser: GetActiveUser,
            private val getTodoListByUser: GetTodoListByUser,
            private val threadTransformer: ThreadTransformer,
            factory: RxDisposableFactory) : UserTodoListContract.Presenter {

    private val disposables: RxDisposables

    init {
        this.disposables = factory.get()
    }

    override fun onAttached() {
        disposables.add(getActiveUser.call().flatMap({ (id) -> getTodoListByUser.call(id) })
                .compose<List<TodoPresentationModel>>(threadTransformer.applySchedulers<List<TodoPresentationModel>>())
                .subscribe(
                        { todos -> view.loadToDoList(todos) }
                ) { error -> Log.e("UserToDo", error.message) })
    }

    override fun onDetached() {
        disposables.clear()
    }
}
