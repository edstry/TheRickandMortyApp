package com.edstry.therickandmortyapp.di

import com.edstry.therickandmortyapp.data.remote.api.RickMortyApi
import com.edstry.therickandmortyapp.data.repository.CharacterRepositoryImpl
import com.edstry.therickandmortyapp.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindCharacterRepository(
        impl: CharacterRepositoryImpl
    ): CharacterRepository

    companion object {
        @Provides
        @Singleton
        fun provideOkHttp(): OkHttpClient {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        }

        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        @Provides
        @Singleton
        fun provideConverterFactory(
            json: Json
        ): Converter.Factory {
            return json.asConverterFactory(
                "application/json".toMediaType()
            )
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            client: OkHttpClient,
            converterFactory: Converter.Factory
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .client(client)
                .addConverterFactory(converterFactory)
                .build()

        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): RickMortyApi =
            retrofit.create(RickMortyApi::class.java)
    }
}