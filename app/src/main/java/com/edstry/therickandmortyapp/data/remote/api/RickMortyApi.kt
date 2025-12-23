package com.edstry.therickandmortyapp.data.remote.api

import com.edstry.therickandmortyapp.data.remote.dto.CharactersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersResponseDto
}