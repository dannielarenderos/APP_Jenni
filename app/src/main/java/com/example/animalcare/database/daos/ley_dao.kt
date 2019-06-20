package com.example.animalcare.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.animalcare.database.entities.ley_entity
import androidx.room.Query

@Dao
interface ley_dao {


    @Query("SELECT * from ley_table")
    fun getAllLeyes(): LiveData<List<ley_entity>>

    @Query("SELECT * from ley_table where articulo = :ley")
    fun getLey(ley:String): LiveData<ley_entity>

    @Query("SELECT * FROM ley_table WHERE apartado LIKE :pattern")
    fun searchLeyPattern(pattern : String): LiveData<List<ley_entity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ley:ley_entity):Long

    @Query("delete from ley_table")
    suspend fun deleteAll()


}