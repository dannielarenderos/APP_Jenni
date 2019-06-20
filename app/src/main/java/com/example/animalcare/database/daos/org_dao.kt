package com.example.animalcare.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.animalcare.database.entities.ley_entity
import androidx.room.Query
import com.example.animalcare.database.entities.org_entity

@Dao
interface org_dao {


    @Query("SELECT * from org_table")
    fun getAllOrgs(): LiveData<List<org_entity>>



    @Query("SELECT * from org_table where nombre_org = :org")
    fun getOrg(org:String): LiveData<org_entity>

}