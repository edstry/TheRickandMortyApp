package com.edstry.therickandmortyapp.domain.repository

import androidx.paging.PagingData
import com.edstry.therickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterPaged(): Flow<PagingData<Character>>
}