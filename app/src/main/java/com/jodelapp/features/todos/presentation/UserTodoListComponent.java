package com.jodelapp.features.todos.presentation;

import com.jodelapp.AppComponent;
import com.jodelapp.di.scope.ViewScope;
import com.jodelapp.features.todos.usecases.UserTodoListUseCaseModule;
import com.jodelapp.user.usecases.UserUseCaseModule;

import dagger.Component;

@ViewScope
@Component(dependencies = AppComponent.class, modules = {UserTodoListModule.class, UserTodoListUseCaseModule.class, UserUseCaseModule.class })
public interface UserTodoListComponent {

    void inject(UserTodoListView view);

}
