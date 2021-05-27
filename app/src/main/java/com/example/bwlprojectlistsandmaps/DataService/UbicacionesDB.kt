package com.example.bwlprojectlistsandmaps.DataService

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bwlprojectlistsandmaps.Model.Ubicacion


@Database(
    entities = [Ubicacion::class],
    version = 1
)

abstract class UbicacionesDB : RoomDatabase() {

    abstract fun ubicDAO(): UbicacionDAO

    companion object{
        private var INSTANCE: UbicacionesDB? = null

        fun getInstance(context: Context): UbicacionesDB {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, UbicacionesDB::class.java, "ubicacion-db").build()
            }

            return INSTANCE!!

        }
    }

}