package com.jodelapp;


import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        initComponents();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }



    private void initComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
