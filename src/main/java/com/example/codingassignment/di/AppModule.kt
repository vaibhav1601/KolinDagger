package com.example.codingassignment.di

import android.content.Context
import androidx.room.Room
import com.example.codingassignment.api.GithubApi
import com.example.codingassignment.data.database.GitRepoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton

    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun geiListApi(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

    @Provides
    @Singleton
    fun provideGitRepoDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GitRepoDataBase::class.java,
        "git_repo_db_name"
    ).fallbackToDestructiveMigration() // this is only for testing,
        //migration will be added for release
        .allowMainThreadQueries() // this solved the issue but remember that its For testing purpose only
        .build();
    @Provides
    @Singleton
    fun provideRepoDao(db: GitRepoDataBase) = db.getRepoDTO() // The reason we can implement a Dao for the database




}


