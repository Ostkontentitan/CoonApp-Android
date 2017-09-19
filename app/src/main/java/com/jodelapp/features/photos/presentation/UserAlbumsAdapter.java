package com.jodelapp.features.photos.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jodelapp.R;
import com.jodelapp.features.photos.models.AlbumPresentationModel;
import com.jodelapp.features.todos.models.TodoPresentationModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<AlbumPresentationModel> albumsDataList = new ArrayList<>();

    public UserAlbumsAdapter(List<AlbumPresentationModel> todoDataList) {
        this.albumsDataList.clear();
        this.albumsDataList.addAll(todoDataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_album, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumPresentationModel albumPresentationModel = albumsDataList.get(position);
        ((AlbumItemViewHolder) holder).render(albumPresentationModel);
    }


    @Override
    public int getItemCount() {
        return albumsDataList.size();
    }


    class AlbumItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemText;

        AlbumItemViewHolder(View view) {
            super(view);
            tvItemText = view.findViewById(R.id.tv_item_title);
        }

        void render(AlbumPresentationModel albumPresentationModel) {
            tvItemText.setText(albumPresentationModel.getTitle());
        }
    }
}
