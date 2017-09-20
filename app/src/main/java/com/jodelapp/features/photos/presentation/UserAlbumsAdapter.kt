package com.jodelapp.features.photos.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.jodelapp.R
import com.jodelapp.features.photos.models.AlbumPresentationModel
import com.jodelapp.features.todos.models.TodoPresentationModel
import com.squareup.picasso.Picasso

import java.util.ArrayList

class UserAlbumsAdapter(todoDataList: List<AlbumPresentationModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var clickListener : (AlbumPresentationModel) -> Unit = { }
    private val albumsDataList = ArrayList<AlbumPresentationModel>()

    init {
        this.albumsDataList.clear()
        this.albumsDataList.addAll(todoDataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlbumItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_album, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val albumPresentationModel = albumsDataList[position]
        (holder as AlbumItemViewHolder).render(albumPresentationModel)
        holder.itemView.setOnClickListener({ clickListener(albumsDataList[holder.adapterPosition]) })
    }

    fun setOnItemClickListener(listener : (AlbumPresentationModel) -> Unit) {
        clickListener = listener
    }

    override fun getItemCount(): Int {
        return albumsDataList.size
    }


    internal inner class AlbumItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvItemText: TextView

        init {
            tvItemText = view.findViewById(R.id.tv_item_title)
        }

        fun render(albumPresentationModel: AlbumPresentationModel) {
            tvItemText.text = albumPresentationModel.title
        }
    }
}
