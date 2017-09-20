package com.jodelapp.features.photos.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jodelapp.App
import com.jodelapp.AppComponent
import com.jodelapp.R
import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.photos.models.PhotoPresentationModel
import kotlinx.android.synthetic.main.fragment_photos.*

import javax.inject.Inject

class UserPhotoListView : Fragment(), UserPhotoListContract.View {

    @Inject
    lateinit var presenter: UserPhotoListContract.Presenter

    private var scopeGraph: UserPhotoListComponent? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_photos, container, false)
        setupScopeGraph(App.get(activity).appComponent)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
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

    private fun setupScopeGraph(appComponent: AppComponent) {
        scopeGraph = DaggerUserPhotoListComponent.builder()
                .appComponent(appComponent)
                .userPhotoListModule(UserPhotoListModule(this))
                .build()
        scopeGraph!!.inject(this)
    }

    override fun loadAlbumsList(albums: List<AlbumPresentationModel>) {
        recycler.layoutManager = LinearLayoutManager(context)
        val adapter = UserAlbumsAdapter(albums)
        recycler.adapter = adapter
        adapter.setOnItemClickListener { presenter.onAlbumClicked(it) }
    }

    override fun loadPhotosGrid(photos: List<PhotoPresentationModel>) {
        recycler.layoutManager = GridLayoutManager(context, 3)
        val adapter = AlbumPhotosAdapter(photos)
        recycler.adapter = adapter
    }

    companion object {

        val instance: UserPhotoListView
            get() = UserPhotoListView()
    }



}
