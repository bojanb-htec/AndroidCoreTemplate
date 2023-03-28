package ___PACKAGE_NAME___.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<TypeT> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TypeT): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<TypeT>): LongArray

    @Update
    suspend fun update(item: TypeT): Int

    @Update
    suspend fun update(items: List<TypeT>): Int

    @Delete
    suspend fun delete(item: TypeT): Int

    @Delete
    suspend fun delete(items: List<TypeT>): Int
}
