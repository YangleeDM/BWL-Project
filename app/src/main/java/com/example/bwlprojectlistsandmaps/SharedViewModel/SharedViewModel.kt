package com.example.bwlprojectlistsandmaps.SharedViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bwlprojectlistsandmaps.Model.Ubicacion

class SharedViewModel : ViewModel() {
    private var ubicacion = MutableLiveData<Ubicacion?>()


    val ubicacionActual : MutableLiveData<Ubicacion?> = ubicacion

    fun setUbi(newUbicacion: Ubicacion?){
        ubicacion.value = newUbicacion
    }

    fun getUbi(): MutableLiveData<Ubicacion?> {
        return ubicacion
    }





}