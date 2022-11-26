package com.example.sbertestapp.di

import android.content.Context
import com.example.sbertestapp.data.datasource.net.RemoteDataSource
import com.example.sbertestapp.data.datasource.net.RemoteDataSourceImpl
import com.example.sbertestapp.data.datasource.net.RetrofitClient
import com.example.sbertestapp.data.datasource.net.RetrofitService
import com.example.sbertestapp.data.models.PhotoModel
import com.example.sbertestapp.data.repositories.Repository
import com.example.sbertestapp.data.repositories.RepositoryImpl
import com.example.sbertestapp.domain.interactors.Interactor
import com.example.sbertestapp.domain.interactors.InteractorImpl
import com.example.sbertestapp.ui.appresources.ResourceManager
import com.example.sbertestapp.ui.appresources.ResourceManagerImpl
import com.example.sbertestapp.ui.entities.Photo
import com.example.sbertestapp.ui.handleerror.FailureFactory
import com.example.sbertestapp.ui.handleerror.FailureHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofitService: RetrofitService): RemoteDataSource<PhotoModel> {
        return RemoteDataSourceImpl(retrofitService)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitService {
        return RetrofitClient().retrofitServices
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource<PhotoModel>): Repository<PhotoModel> {
        return RepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    @Provides
    @Singleton
    fun provideFailureHandler(resourceManager: ResourceManager): FailureHandler {
        return FailureFactory(resourceManager)
    }

    @Provides
    @Singleton
    fun provideInteractor(repository: Repository<PhotoModel>, failureHandler: FailureHandler): Interactor<List<Photo>> {
        return InteractorImpl(repository, failureHandler)
    }
}