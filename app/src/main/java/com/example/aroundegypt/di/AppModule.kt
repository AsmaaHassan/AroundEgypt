package com.example.github.di


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
@InstallIn(SingletonComponent::class) // This ensures the module lives as long as the application
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
    fun provideRepository(api: ExperienceApi): ExperienceRepository =
        ExperienceRepositoryImpl(api)

    @Provides @Singleton
    fun provideUseCases(repo: ExperienceRepository) = UseCases(
        getRecommendedExpUseCase = GetRecommendedExpUseCase(repo),
        getRecentExpUseCase = GetRecentExpUseCase(repo),
        searchExperienceUseCase = SearchExperienceUseCase(repo),
        likeExperience = LikeExperienceUseCase(repo)

    )

    @Provides
    @Singleton
    fun provideOkHttp(logging: HttpLoggingInterceptor
    ): OkHttpClient {
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

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://github.com/")
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()


}

data class UseCases(
    val getRecommendedExpUseCase: GetRecommendedExpUseCase,
    val getRecentExpUseCase: GetRecentExpUseCase,
    val searchExperienceUseCase: SearchExperienceUseCase,
    val likeExperience: LikeExperienceUseCase
)