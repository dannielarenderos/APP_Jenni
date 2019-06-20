package com.example.animalcare.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "ley_table")
data class ley_entity (
    @PrimaryKey @ColumnInfo (name = "id") val id: String,
    @field:Json(name="apartado")
    @ColumnInfo(name = "apartado") val apartado: String,
    @field:Json(name="articulo")
    @ColumnInfo(name = "articulo") val articulo: String
    )