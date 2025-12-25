package com.edstry.therickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edstry.therickandmortyapp.data.local.dao.CharacterDao
import com.edstry.therickandmortyapp.data.local.dao.RemoteKeysDao
import com.edstry.therickandmortyapp.data.local.entity.CharacterEntity
import com.edstry.therickandmortyapp.data.local.entity.RemoteKeysEntity

@Database(
    entities = [CharacterEntity::class, RemoteKeysEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}