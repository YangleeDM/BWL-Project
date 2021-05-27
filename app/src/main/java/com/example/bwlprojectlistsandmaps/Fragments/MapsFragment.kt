package com.example.bwlprojectlistsandmaps.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.bwlprojectlistsandmaps.R
import com.example.bwlprojectlistsandmaps.SharedViewModel.SharedViewModel
import com.example.bwlprojectlistsandmaps.Model.Ubicacion
import com.example.bwlprojectlistsandmaps.DataService.UbicacionDAO
import com.example.bwlprojectlistsandmaps.DataService.UbicacionesDB
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MapsFragment : Fragment(), OnMapReadyCallback {

    //Variable para saber cuantas ubicaciones hay
    private lateinit var isUbicacionInDb : List<Ubicacion>

    // Variable para obtener la instancia
    lateinit var ubicacionDAO: UbicacionDAO

    //Variable de ubicación específica
    private lateinit var ubicacionEnMapa : MutableLiveData<Ubicacion?>

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var mapaGoogle : GoogleMap
    lateinit var ubicacionFinal : Ubicacion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstance: Bundle?): View?{

        val view : View  = inflater.inflate(R.layout.fragment_maps,container,false)

        //Instanciación de viewmodel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Casteando el mapa como SupportMapFragment (esto es muy diferente a como se hace desde una activity T_T)
        var supportMapFragment : SupportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        //Instancia para acceder a la base de datos mediante ROOM
        ubicacionDAO = UbicacionesDB.getInstance(requireContext().applicationContext).ubicDAO()

        CoroutineScope(Dispatchers.IO).launch {
            isUbicacionInDb = ubicacionDAO.getAll()
        }


        // Recibo la ubicación que fue seteada desde HomeFragment
        ubicacionEnMapa = sharedViewModel.getUbi()

        if (ubicacionEnMapa.value != null){
            ubicacionFinal = ubicacionEnMapa.value!!
        }



        return view
    }



    override fun onMapReady(p0: GoogleMap) {
        mapaGoogle = p0

        // Una sola ubicación seleccionada: Se muestra un marcador solamente en esa ubicación
        if (ubicacionEnMapa.value != null){
            createMarker(ubicacionFinal.direccion,ubicacionFinal.latitud,ubicacionFinal.longitud)
        }else{ //Ninguna ubicación seleccionada: Se muestran todas las ubicaciones marcadas

            for (i in isUbicacionInDb.indices){
                createMarker(isUbicacionInDb[i].direccion,isUbicacionInDb[i].latitud,isUbicacionInDb[i].longitud)
            }

        }

    }


    private fun createMarker(nombre: String, lat: String, long: String) {
        val cordinates = LatLng(lat.toDouble(), long.toDouble())
        val marker = MarkerOptions().position(cordinates).title(nombre)
        mapaGoogle.addMarker(marker)

        mapaGoogle.animateCamera(
            CameraUpdateFactory.newLatLngZoom(cordinates, 16f), 2500, null
        )
    }


}

