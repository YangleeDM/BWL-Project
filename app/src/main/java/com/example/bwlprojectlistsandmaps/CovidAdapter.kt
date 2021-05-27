package com.example.bwlprojectlistsandmaps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bwlprojectlistsandmaps.Model.CountriesItem
import com.example.bwlprojectlistsandmaps.Model.CovidResponseDataClass
import kotlinx.android.synthetic.main.item_casos_covid.view.*

class CovidAdapter(val casosCovid : List<CountriesItem>) : RecyclerView.Adapter<CovidAdapter.CovidViewHolder>(){


    class CovidViewHolder(val view: View): RecyclerView.ViewHolder(view) {


        fun render(countriesItem: CountriesItem){
            view.countryItem.text = countriesItem.country
            view.casesCountItem.text  = countriesItem.totalConfirmed.toString()
            view.deathCountItem.text = countriesItem.totalDeaths.toString()
            view.recoveredsCount.text = countriesItem.totalRecovered.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return CovidViewHolder(layoutInflater.inflate(R.layout.item_casos_covid, parent,false))
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {

        holder.render(casosCovid[position])
    }

    override fun getItemCount(): Int = casosCovid.size
}