package com.edstry.therickandmortyapp.data.mapper

import com.edstry.therickandmortyapp.data.remote.dto.CharacterDto
import com.edstry.therickandmortyapp.domain.model.Character

fun CharacterDto.toDomain(): Character =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )