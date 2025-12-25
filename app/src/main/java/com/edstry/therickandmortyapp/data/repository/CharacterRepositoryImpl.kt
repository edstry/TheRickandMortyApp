package com.edstry.therickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.edstry.therickandmortyapp.data.remote.api.RickMortyApi
import com.edstry.therickandmortyapp.data.remote.paging.CharactersPagingSource
import com.edstry.therickandmortyapp.domain.model.Character
import com.edstry.therickandmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickMortyApi
) : CharacterRepository {

    override fun getCharacterPaged(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { CharactersPagingSource(api) }
        ).flow
    }
}