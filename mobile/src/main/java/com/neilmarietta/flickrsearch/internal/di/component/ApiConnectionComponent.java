package com.neilmarietta.flickrsearch.internal.di.component;

import com.neilmarietta.flickrsearch.internal.di.module.ApiConnectionModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApiConnectionModule.class})
public interface ApiConnectionComponent {
    // Provide instance for downstream components
    Retrofit retrofit();
}
