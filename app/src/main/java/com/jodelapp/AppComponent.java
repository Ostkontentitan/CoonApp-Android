package com.jodelapp;


import android.content.Context;
import android.content.res.Resources;

import com.jodelapp.data.DataComponent;
import com.jodelapp.data.DataModule;
import com.jodelapp.user.UserManagerComponent;
import com.jodelapp.user.UserManagerModule;
import com.jodelapp.utilities.UtilsComponent;
import com.jodelapp.utilities.UtilsModule;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class,  DataModule.class, UserManagerModule.class})
public interface AppComponent extends UtilsComponent, DataComponent, UserManagerComponent {

    void inject(App app);

    // expose dependencies to scope graph
    Context exposeAppContext();

    Resources exposeResources();

    Locale exposeLocale();

}
