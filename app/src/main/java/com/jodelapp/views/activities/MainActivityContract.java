package com.jodelapp.views.activities;


public interface MainActivityContract {

    interface View {


        void loadToDoPage();

        void setBottomNavigationActiveOnToDo();

        void replaceWithPhotosPage();

        void replaceWithProfilePage();

        void replaceWithToDoPage();
    }

    interface Presenter {

        void onCreate(boolean hasSavedInstanceState);

        void onDestroy();

        void onNavigationItemSelected(int itemId);
    }
}
