package com.jodelapp.features.user_profile.presentation

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
import com.jodelapp.features.photos.presentation.*
import com.jodelapp.features.todos.presentation.UserProfilesAdapter
import com.jodelapp.features.user_profile.models.UserPresentationModel
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.fragment_user_profile.*
import javax.inject.Inject

/**
 * Created by ottek on 20.09.2017.
 */
class UserProfileView  : Fragment(), UserProfileContract.View {

    @Inject
    lateinit var presenter: UserProfileContract.Presenter

    private var scopeGraph: UserProfileComponent? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, bundle: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_user_profile, container, false)
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
        scopeGraph = DaggerUserProfileComponent.builder()
                .appComponent(appComponent)
                .userProfileModule(UserProfileModule(this))
                .build()
        scopeGraph!!.inject(this)
    }

    override fun listUserProfiles(userProfiles: List<UserPresentationModel>) {
        recycler_profiles.layoutManager = LinearLayoutManager(context)
        val adapter = UserProfilesAdapter(userProfiles)
        adapter.setOnItemClickListener { presenter.onUserProfileClicked(it) }
        recycler_profiles.adapter = adapter
    }

    override fun showDetailsForUserProfile(activeUserProfile: UserPresentationModel) {
        tv_user_fullname.text = activeUserProfile.fullname
        tv_user_address.text = activeUserProfile.fulladdress
        tv_user_company.text = activeUserProfile.companyDetails
        tv_user_contact.text = activeUserProfile.contactDetails
    }

    companion object {

        val instance: UserProfileView
            get() = UserProfileView()
    }
}
