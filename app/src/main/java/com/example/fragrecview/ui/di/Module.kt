package com.example.fragrecview.ui.di

import android.content.Context
import com.example.fragrecview.data.local.userdata.UserDao
import com.example.fragrecview.data.local.userdata.UserDatabase
import com.example.fragrecview.data.local.userposts.PostsDao
import com.example.fragrecview.data.remote.ApiService
import com.example.fragrecview.data.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return UserDatabase.getDatabase(appContext)
    }

    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Provides
    fun providesPostsDao(userDatabase: UserDatabase): PostsDao {
        return userDatabase.postsDao()
    }

    @Provides
    fun providesBaseUrl(): String {
        return "https://jsonplaceholder.typicode.com/"
    }

    @Provides
    fun providesClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun providesRetrofitInstance(baseURL: String, client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun providesPostsRepository(apiService: ApiService, postsDao: PostsDao): PostsRepository {
        val postsRepository = PostsRepository(apiService, postsDao)
        return postsRepository
    }
}