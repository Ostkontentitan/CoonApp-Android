package com.jodelapp.features.photos.presentation;

import com.jodelapp.AppComponent;
import com.jodelapp.di.scope.ViewScope;
import com.jodelapp.features.photos.usecases.UserPhotoListUseCaseModule;
import com.jodelapp.user.usecases.UserUseCaseModule;

import dagger.Component;

@ViewScope
@Component(dependencies = AppComponent.class, modules = {UserPhotoListModule.class, UserPhotoListUseCaseModule.class, UserUseCaseModule.class})
public interface UserPhotoListComponent {
    void inject(UserPhotoListView view);
}