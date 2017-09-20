package com.jodelapp.features.todos.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jodelapp.R
import com.jodelapp.features.user_profile.models.UserPresentationModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class UserProfilesAdapter(todoDataList: List<UserPresentationModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var clickListener : (UserPresentationModel) -> Unit = { }
    private val UserProfilesDataList = ArrayList<UserPresentationModel>()

    init {
        this.UserProfilesDataList.clear()
        this.UserProfilesDataList.addAll(todoDataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserProfileItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_profile, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val UserPresentationModel = UserProfilesDataList[position]
        (holder as UserProfileItemViewHolder).render(UserPresentationModel)
        holder.itemView.setOnClickListener({ clickListener(UserProfilesDataList[holder.adapterPosition]) })
    }

    fun setOnItemClickListener(listener : (UserPresentationModel) -> Unit) {
        clickListener = listener
    }

    override fun getItemCount(): Int {
        return UserProfilesDataList.size
    }


    internal inner class UserProfileItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItemText: TextView = view.findViewById(R.id.tv_item_title)


        fun render(UserPresentationModel: UserPresentationModel) {
            tvItemText.text = UserPresentationModel.fullname
        }
    }
}