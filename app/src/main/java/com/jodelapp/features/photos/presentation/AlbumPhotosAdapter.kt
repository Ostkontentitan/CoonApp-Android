package com.jodelapp.features.photos.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jodelapp.R
import com.jodelapp.features.photos.models.PhotoPresentationModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class AlbumPhotosAdapter(todoDataList: List<PhotoPresentationModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var clickListener : (PhotoPresentationModel) -> Unit = { }
    private val photosDataList = ArrayList<PhotoPresentationModel>()

    init {
        this.photosDataList.clear()
        this.photosDataList.addAll(todoDataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_photo, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photoPresentationModel = photosDataList[position]
        (holder as PhotoItemViewHolder).render(photoPresentationModel)
        holder.itemView.setOnClickListener({ clickListener(photosDataList[holder.adapterPosition]) })
    }

    fun setOnItemClickListener(listener : (PhotoPresentationModel) -> Unit) {
        clickListener = listener
    }

    override fun getItemCount(): Int {
        return photosDataList.size
    }


    internal inner class PhotoItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItemText: TextView = view.findViewById(R.id.tv_item_title)
        val ivThumb: ImageView = view.findViewById(R.id.iv_item_thumb)


        fun render(photoPresentationModel: PhotoPresentationModel) {
            tvItemText.text = photoPresentationModel.title
            Picasso.with(itemView.context).load(photoPresentationModel.thumbUrl).into(ivThumb)
        }
    }
}