package com.edstry.therickandmortyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edstry.therickandmortyapp.data.local.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys WHERE characterId = :id")
    suspend fun remoteKeysByCharacterId(id: Int): RemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keys: List<RemoteKeysEntity>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}