package com.jodelapp.features.user_profile.presentation;

import com.jodelapp.AppComponent;
import com.jodelapp.di.scope.ViewScope;
import com.jodelapp.user.usecases.UserUseCaseModule;
import com.jodelapp.features.user_profile.usecases.UserProfileUseCaseModule;

import dagger.Component;

@ViewScope
@Component(dependencies = AppComponent.class, modules = {UserProfileModule.class, UserProfileUseCaseModule.class, UserUseCaseModule.class })
public interface UserProfileComponent {
    void inject(UserProfileView view);
}
