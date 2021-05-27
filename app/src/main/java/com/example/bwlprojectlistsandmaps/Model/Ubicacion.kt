package com.example.bwlprojectlistsandmaps.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ubicacion (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val direccion : String,
    val latitud : String,
    val longitud : String

)