package com.edstry.therickandmortyapp.domain.usecase

import androidx.paging.PagingData
import com.edstry.therickandmortyapp.domain.model.Character
import com.edstry.therickandmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersPagedUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<Character>> {
        return repository.getCharacterPaged()
    }
}