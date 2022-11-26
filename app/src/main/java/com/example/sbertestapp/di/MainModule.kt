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

@Module
class MainModule {

    fun provideRemoteDataSource(retrofitService: RetrofitService): RemoteDataSource<PhotoModel> {
        return RemoteDataSourceImpl(retrofitService)
    }

    fun provideRetrofit(): RetrofitService {
        return RetrofitClient().retrofitServices
    }

    fun provideRepository(remoteDataSource: RemoteDataSource<PhotoModel>): Repository<PhotoModel> {
        return RepositoryImpl(remoteDataSource)
    }

    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManagerImpl(context)
    }

    fun provideFailureHandler(resourceManager: ResourceManager): FailureHandler {
        return FailureFactory(resourceManager)
    }

    fun provideInteractor(repository: Repository<PhotoModel>, failureHandler: FailureHandler): Interactor<List<Photo>> {
        return InteractorImpl(repository, failureHandler)
    }
}