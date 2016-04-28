package com.neilmarietta.flickrsearch;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.neilmarietta.flickrsearch.internal.di.component.ApiConnectionComponent;
import com.neilmarietta.flickrsearch.internal.di.component.DaggerApiConnectionComponent;
import com.neilmarietta.flickrsearch.internal.di.module.AndroidModule;
import com.neilmarietta.flickrsearch.internal.di.component.ApplicationComponent;
import com.neilmarietta.flickrsearch.internal.di.component.DaggerApplicationComponent;

public class FlickrSearchApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private ApiConnectionComponent mApiConnectionComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();

        mApiConnectionComponent = DaggerApiConnectionComponent.create();

        Fresco.initialize(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public ApiConnectionComponent getApiConnectionComponent() {
        return mApiConnectionComponent;
    }

    public static FlickrSearchApplication from(@NonNull Context context) {
        return (FlickrSearchApplication) context.getApplicationContext();
    }
}
