package com.jodelapp.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.jodelapp.App
import com.jodelapp.AppComponent
import com.jodelapp.R
import com.jodelapp.features.photos.presentation.UserPhotoListView
import com.jodelapp.features.todos.presentation.UserTodoListView

import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    @Inject
    lateinit var presenter: MainActivityContract.Presenter

    private var scopeGraph: MainActivityComponent? = null

    override fun loadToDoPage() {
        supportFragmentManager.beginTransaction()
                .add(R.id.v_container, UserTodoListView.instance)
                .commit()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupScopeGraph(App.get(this).appComponent)
        initViews()
        val hasSavedInstanceState = savedInstanceState != null
        presenter.onCreate(hasSavedInstanceState)
    }


    public override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


    private fun setupScopeGraph(appComponent: AppComponent) {
        scopeGraph = DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(MainActivityModule(this))
                .build()
        scopeGraph!!.inject(this)
    }

    private fun initViews() {
        setSupportActionBar(tb_app as Toolbar?)
        bottom_navigation.setOnNavigationItemSelectedListener({
            item -> presenter.onNavigationItemSelected(item.itemId)
            true
        })
    }

    override fun setBottomNavigationActiveOnToDo() {
        bottom_navigation.selectedItemId = R.id.bn_tasks
    }

    override fun replaceWithPhotosPage() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.v_container, UserPhotoListView.instance)
                .commit()
    }

    override fun replaceWithProfilePage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replaceWithToDoPage() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.v_container, UserTodoListView.instance)
                .commit()
    }

}
