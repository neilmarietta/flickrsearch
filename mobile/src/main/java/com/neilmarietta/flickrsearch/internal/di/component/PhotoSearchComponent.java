package com.neilmarietta.flickrsearch.internal.di.component;

import com.neilmarietta.flickrsearch.internal.di.PerActivity;
import com.neilmarietta.flickrsearch.internal.di.module.PhotoSearchModule;
import com.neilmarietta.flickrsearch.presentation.view.fragment.FlickrPhotoListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = {ApiConnectionComponent.class}, modules = {PhotoSearchModule.class})
public interface PhotoSearchComponent {
    void inject(FlickrPhotoListFragment fragment);
}
