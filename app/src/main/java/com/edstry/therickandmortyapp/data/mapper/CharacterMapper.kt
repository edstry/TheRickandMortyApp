package com.edstry.therickandmortyapp.data.mapper

import com.edstry.therickandmortyapp.data.local.entity.CharacterEntity
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


fun CharacterDto.toEntity(now: Long): CharacterEntity =
    CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        lastUpdated = now
    )

fun CharacterEntity.toDomain(): Character =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )