package com.example.bwlprojectlistsandmaps.Fragments

import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bwlprojectlistsandmaps.CovidAdapter
import com.example.bwlprojectlistsandmaps.DataService.APIService
import com.example.bwlprojectlistsandmaps.Model.CovidResponseDataClass
import com.example.bwlprojectlistsandmaps.R
import com.example.bwlprojectlistsandmaps.UbicacionesAdapter
import kotlinx.android.synthetic.main.error_dialog_ok.*
import kotlinx.android.synthetic.main.fragment_covid.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Error


class CovidFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Valicación por si no hay internet...
        if(!checkNetworkConnection()){
            showErrorAndOKDialog()

        }else{
            // Llamada asincróna mediante corrutinas
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO){

                    getRetrofit().create(APIService::class.java).getCasosCovid(getString(R.string.url_last))

                }
                val casosCovid = response.body()

                if (response.isSuccessful){

                    progressCovid.visibility = View.GONE
                    recyclerCovid.visibility = View.VISIBLE
                    recyclerCovid.layoutManager = LinearLayoutManager(requireContext().applicationContext)
                    val adapter = CovidAdapter(casosCovid!!.countries)
                    recyclerCovid.adapter = adapter

                }else{ // Cuadro de díalogo en caso de haber un error
                    showErrorAndOKDialog()
                }
            }
        }




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid, container, false)
    }


    //Cuadro de díalogo para error...
    private lateinit var errorOkDialog: Dialog
    private fun showErrorAndOKDialog() {
        errorOkDialog = Dialog(requireContext())
        errorOkDialog.setContentView(R.layout.error_dialog_ok)
        //Set info
        errorOkDialog.infoError.text = getString(R.string.error_message)
        errorOkDialog.show() // Se muestra el cuadro de dialogo

        errorOkDialog.setCanceledOnTouchOutside(false) // No se puede tocar a fuera para salir

        // On OK: Desaparece el dialogo
        errorOkDialog.okBtn.setOnClickListener {
            errorOkDialog.dismiss()
        }
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(getString(R.string.baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Checa si hay internet
    fun checkNetworkConnection(): Boolean {
        val connMgr = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        val isConnected: Boolean = if(networkInfo != null) networkInfo.isConnected() else false
        return isConnected
    }

}