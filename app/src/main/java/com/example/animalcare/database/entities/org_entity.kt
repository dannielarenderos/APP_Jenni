package com.example.animalcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "org_table")
data class org_entity (

    @PrimaryKey @ColumnInfo (name = "id") val id: String,
    @field:Json(name="nombre_org")
    @ColumnInfo(name = "nombre_org") val nombre_ley: String,
    @field:Json(name="telefono_org")
    @ColumnInfo(name = " telefono_org") val telefono_org: String,
    @field:Json(name="direccion_org")
    @ColumnInfo(name = "direccion_org") val direccion_org: String

    )