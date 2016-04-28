package com.neilmarietta.flickrsearch.internal.di.module;

import com.neilmarietta.flickrsearch.data.repository.PhotoSearchResultRepository;
import com.neilmarietta.flickrsearch.interactor.PhotoSearchUseCase;
import com.neilmarietta.flickrsearch.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoSearchModule {

    @Provides
    @PerActivity
    PhotoSearchUseCase providePhotoSearchUseCase(PhotoSearchResultRepository photoSearchResultRepository) {
        return new PhotoSearchUseCase(photoSearchResultRepository);
    }
}
