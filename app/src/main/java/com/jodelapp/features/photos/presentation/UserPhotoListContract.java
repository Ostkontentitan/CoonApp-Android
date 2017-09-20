package com.jodelapp.features.photos.presentation;


import com.jodelapp.features.photos.models.AlbumPresentationModel;
import com.jodelapp.features.photos.models.PhotoPresentationModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface UserPhotoListContract {

    interface View {
        void loadAlbumsList(List<AlbumPresentationModel> todos);
        void loadPhotosGrid(List<PhotoPresentationModel> photos);
    }

    interface Presenter {
        void onAttached();

        void onDetached();

        void onAlbumClicked(@NotNull AlbumPresentationModel album);
    }
}
