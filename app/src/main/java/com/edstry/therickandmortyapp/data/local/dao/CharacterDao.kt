package com.edstry.therickandmortyapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edstry.therickandmortyapp.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()

    @Query("SELECT MIN(lastUpdated) FROM characters")
    suspend fun getOldestUpdateTime(): Long?
}