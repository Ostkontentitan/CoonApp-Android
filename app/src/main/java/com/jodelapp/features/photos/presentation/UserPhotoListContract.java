package com.jodelapp.features.photos.presentation;


import com.jodelapp.features.photos.models.AlbumPresentationModel;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface UserPhotoListContract {

    interface View {
        void loadAlbumsList(List<AlbumPresentationModel> todos);
    }

    interface Presenter {
        void onAttached();

        void onDetached();
    }
}
