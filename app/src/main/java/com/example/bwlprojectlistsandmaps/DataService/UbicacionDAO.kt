package com.example.bwlprojectlistsandmaps.DataService

import androidx.room.*
import com.example.bwlprojectlistsandmaps.Model.Ubicacion

@Dao
interface UbicacionDAO {

    @Query("SELECT * FROM Ubicacion")
    fun getAll(): List<Ubicacion>

    @Query("SELECT * FROM Ubicacion WHERE id = :id")
    fun getById(id: Int): Ubicacion

    @Update
    fun update(ubicacion: Ubicacion)

    @Insert
    fun insertUbi(ubicacion: Ubicacion)

    @Delete
    fun delete(ubicacion: Ubicacion)
}