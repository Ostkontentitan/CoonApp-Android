package com.jodelapp.features.todos.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jodelapp.App
import com.jodelapp.AppComponent
import com.jodelapp.R
import com.jodelapp.features.todos.models.TodoPresentationModel
import kotlinx.android.synthetic.main.fragment_todos.*

import javax.inject.Inject

class UserTodoListView : Fragment(), UserTodoListContract.View {

    @Inject
    lateinit var presenter: UserTodoListContract.Presenter

    private var scopeGraph: UserTodoListComponent? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_todos, container, false)
        setupScopeGraph(App.get(activity).appComponent)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        ls_user_todos.layoutManager = LinearLayoutManager(activity)
        ls_user_todos.setHasFixedSize(true)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.onAttached()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetached()
    }

    override fun loadToDoList(toDos: List<TodoPresentationModel>) {
        val adapter = UserTodoListAdapter(toDos)
        ls_user_todos.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setupScopeGraph(appComponent: AppComponent) {
        scopeGraph = DaggerUserTodoListComponent.builder()
                .appComponent(appComponent)
                .userTodoListModule(UserTodoListModule(this))
                .build()
        scopeGraph!!.inject(this)
    }

    companion object {
        val instance: UserTodoListView
            get() = UserTodoListView()
    }
}
