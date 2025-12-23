package com.example.github.di

import android.content.Context
import androidx.room.Room
import com.example.aroundegypt.data.datasources.local.AppDatabase
import com.example.aroundegypt.data.datasources.local.dao.ExperienceDao
import com.example.aroundegypt.domain.usecase.GetRecentExpUseCase
import com.example.aroundegypt.domain.usecase.LikeExperienceUseCase
import com.example.aroundegypt.domain.usecase.SearchExperienceUseCase
import com.example.github.data.datasources.remote.api.ExperienceApi
import com.example.github.data.repository.ExperienceRepositoryImpl
import com.example.github.domain.repository.ExperienceRepository
import com.example.github.domain.usecase.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides @Singleton
    fun provideGithubApi(okHttp: OkHttpClient, moshi: Moshi): ExperienceApi =
        Retrofit.Builder()
            .baseUrl("https://aroundegypt.34ml.com/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttp)
            .build()
            .create(ExperienceApi::class.java)

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "around_egypt_db"
        ).build()

    @Provides @Singleton
    fun provideExperienceDao(database: AppDatabase): ExperienceDao =
        database.experienceDao()

    @Provides @Singleton
    fun provideRepository(api: ExperienceApi, dao: ExperienceDao): ExperienceRepository =
        ExperienceRepositoryImpl(api, dao)

    @Provides @Singleton
    fun provideUseCases(repo: ExperienceRepository) = UseCases(
        getRecommendedExpUseCase = GetRecommendedExpUseCase(repo),
        getRecentExpUseCase = GetRecentExpUseCase(repo),
        searchExperienceUseCase = SearchExperienceUseCase(repo),
        likeExperience = LikeExperienceUseCase(repo)
    )

    @Provides
    @Singleton
    fun provideOkHttp(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()
    }
}

data class UseCases(
    val getRecommendedExpUseCase: GetRecommendedExpUseCase,
    val getRecentExpUseCase: GetRecentExpUseCase,
    val searchExperienceUseCase: SearchExperienceUseCase,
    val likeExperience: LikeExperienceUseCase
)
