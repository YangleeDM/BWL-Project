package com.example.bwlprojectlistsandmaps.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bwlprojectlistsandmaps.CovidAdapter
import com.example.bwlprojectlistsandmaps.DataService.APIService
import com.example.bwlprojectlistsandmaps.Model.CovidResponseDataClass
import com.example.bwlprojectlistsandmaps.R
import com.example.bwlprojectlistsandmaps.UbicacionesAdapter
import kotlinx.android.synthetic.main.fragment_covid.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create




class CovidFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Llamada asincróna mediante corrutinas
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO){

                getRetrofit().create(APIService::class.java).getCasosCovid("summary")

            }
            val casosCovid = response.body()

            if (response.isSuccessful){

                progressCovid.visibility = View.GONE
                recyclerCovid.visibility = View.VISIBLE
                recyclerCovid.layoutManager = LinearLayoutManager(requireContext().applicationContext)
                val adapter = CovidAdapter(casosCovid!!.countries)
                recyclerCovid.adapter = adapter

            }else{ // Cuadro de díalogo en caso de haber un error
                println("ERROR")
                println("ERROR")
                println("ERROR")
                println("ERROR")
                println("ERROR")
                println("ERROR")
            }
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid, container, false)
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(getString(R.string.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}