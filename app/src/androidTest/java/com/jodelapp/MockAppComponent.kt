package com.jodelapp

import android.content.Context
import android.content.res.Resources

import com.jodelapp.App
import com.jodelapp.AppComponent
import com.jodelapp.AppModule
import com.jodelapp.data.DataComponent
import com.jodelapp.user.UserManagerComponent
import com.jodelapp.user.UserManagerModule
import com.jodelapp.utilities.UtilsComponent
import com.jodelapp.utilities.UtilsModule
import com.jodelapp.data.MockDataModule
import com.jodelapp.views.activities.MainActivityTest

import java.util.Locale

import javax.inject.Singleton

import dagger.Component

/**
 * Created by ottek on 20.09.2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, UtilsModule::class, MockDataModule::class, UserManagerModule::class))
interface MockAppComponent : AppComponent, UtilsComponent, DataComponent, UserManagerComponent {

}