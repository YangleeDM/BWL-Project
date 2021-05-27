package com.example.bwlprojectlistsandmaps.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bwlprojectlistsandmaps.*
import com.example.bwlprojectlistsandmaps.DataService.UbicacionDAO
import com.example.bwlprojectlistsandmaps.DataService.UbicacionesDB
import com.example.bwlprojectlistsandmaps.Model.Ubicacion
import com.example.bwlprojectlistsandmaps.SharedViewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class HomeFragment : Fragment(), UbicacionesAdapter.OnItemClickListener {

    // Ubicaciónes...

    val ubicacionUno = Ubicacion(1, "Takasaky", "18.148455984453875", "-94.42779134682301")
    val ubicacionDos = Ubicacion(2, "Infonavit", "18.148517155071204", "-94.42401479655108")
    val ubicaciontres = Ubicacion(3, "Ayuntamiento", "18.149251200809427", "-94.42530225687105")
    val ubicacionCinco = Ubicacion(5, "Bethany Sanatorium", "18.148338740712646", "-94.42717443875529")
    val ubicacionCuatro = Ubicacion(4, "Hospital regional", "18.145540160199133", "-94.42433666163106")
    val ubicacionSeis = Ubicacion(6, "Casa bendición", "18.147778008680163", "-94.42739974431127")


    // Variable para obtener la instancia
    lateinit var ubicacionDAO: UbicacionDAO

    var ubicaciones : List<Ubicacion>? = null
    val mapsFragment = MapsFragment()


    private lateinit var sharedViewModel: SharedViewModel

            override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstance: Bundle?): View?{


                sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

                ubicacionDAO = UbicacionesDB.getInstance(requireContext().applicationContext).ubicDAO()


            // Se obtienen las ubicaciones por medio de un hilo secundario en corrutinas

            CoroutineScope(Dispatchers.Main).launch {

                // Primero obtengo las ubicaciones para validar si ya estan insertadas o no
                ubicaciones = withContext(Dispatchers.IO){
                    ubicacionDAO.getAll()

                }

                // Si están vacías, las inserto
                if (ubicaciones!!.isNullOrEmpty()){
                    insertUbis()
                    firtsTimeLayout.visibility = View.VISIBLE
                    return@launch
                }else{
                    firtsTimeLayout.visibility = View.GONE
                    recyclerViewUbicaciones.visibility = View.VISIBLE
                    recyclerViewUbicaciones.layoutManager = LinearLayoutManager(requireContext().applicationContext)
                    val adapter = UbicacionesAdapter(ubicaciones!!, this@HomeFragment)
                    recyclerViewUbicaciones.adapter = adapter
                }


            }


        return inflater.inflate(R.layout.fragment_home,container,false)



    }



    override fun onItemClick(ubicacionSeleccionada: Ubicacion) {

        // Seteo la ubicación en la instancia del viewmodel para recibirla desde  MapsFragment
        sharedViewModel.setUbi(ubicacionSeleccionada)


        // Inicializar fragment mapas
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.wrapper, mapsFragment)
            commit()
        }
    }

    fun insertUbis(){
        CoroutineScope(Dispatchers.IO).launch {
            ubicacionDAO.insertUbi(ubicacionUno)
            ubicacionDAO.insertUbi(ubicacionDos)
            ubicacionDAO.insertUbi(ubicaciontres)
            ubicacionDAO.insertUbi(ubicacionCuatro)
            ubicacionDAO.insertUbi(ubicacionCinco)
            ubicacionDAO.insertUbi(ubicacionSeis)

        }

    }




}