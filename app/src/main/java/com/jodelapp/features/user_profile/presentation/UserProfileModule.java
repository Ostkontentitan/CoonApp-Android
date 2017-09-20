package com.jodelapp.features.user_profile.presentation;


import com.jodelapp.features.photos.presentation.UserPhotoListContract;
import com.jodelapp.features.photos.presentation.UserPhotoListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class UserProfileModule {

    private final UserProfileContract.View view;

    public UserProfileModule(UserProfileContract.View view) {
        this.view = view;
    }

    @Provides
    public UserProfileContract.View provideView() {
        return this.view;
    }

    @Provides
    public UserProfileContract.Presenter providePresenter(UserProfilePresenter presenter) {
        return presenter;
    }
}
