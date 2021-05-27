package com.example.bwlprojectlistsandmaps


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bwlprojectlistsandmaps.SharedViewModel.SharedViewModel
import com.example.bwlprojectlistsandmaps.Fragments.CovidFragment
import com.example.bwlprojectlistsandmaps.Fragments.HomeFragment
import com.example.bwlprojectlistsandmaps.Fragments.MapsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    // Variables de cada fragment
    val homeFragment = HomeFragment()
    val mapsFragment = MapsFragment()
    val covidFragment = CovidFragment()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SharedViewModel
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)


        // Por defecto la vista principal es el HomeFragment
        makeCurrentFragment(homeFragment)

        // título
        setTitle(getString(R.string.app_name))

        // Cada vez que el usuario toque un icono, se cambia de fragment
        bottom_nav.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ic_home ->{
                    setTitle(getString(R.string.home))
                    makeCurrentFragment(homeFragment)
                }
                R.id.ic_maps ->{
                    //Setear ubicacion en nula o vacía para cargar todos los Markers en el mapa ya que,
                    // esto entrando desde el bottom_navigation
                    sharedViewModel.setUbi(null)
                    setTitle(getString(R.string.map))
                    makeCurrentFragment(mapsFragment)
                }
                R.id.ic_covid ->{
                    // Abrir fragment de casos covid
                    setTitle(R.string.covid)
                    makeCurrentFragment(covidFragment)

                }
            }
            true
        }

    }

    // Función para cambiar de fragment
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.wrapper, fragment)
            commit()
        }
    }

    fun onCargarUbis(view: View) {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
        setTitle(R.string.home)

    }
}