package com.neilmarietta.flickrsearch.internal.di.component;

import android.app.Application;

import com.neilmarietta.flickrsearch.internal.di.module.AndroidModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AndroidModule.class})
public interface ApplicationComponent {
    Application application();
}
