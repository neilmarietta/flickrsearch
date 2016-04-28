package com.neilmarietta.flickrsearch.internal.di.component;

import com.neilmarietta.flickrsearch.data.repository.PhotoSearchResultRepository;
import com.neilmarietta.flickrsearch.internal.di.module.ApiConnectionModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiConnectionModule.class})
public interface RepositoriesComponent {
    PhotoSearchResultRepository photoSearchResultRepository();
}
