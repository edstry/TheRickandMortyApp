package com.edstry.therickandmortyapp.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.edstry.therickandmortyapp.domain.model.Character
import com.edstry.therickandmortyapp.domain.usecase.GetCharactersPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersPaged: GetCharactersPagedUseCase
): ViewModel() {

    // Paging flow
    val characters: Flow<PagingData<Character>> =
        getCharactersPaged()
            .cachedIn(viewModelScope)
}