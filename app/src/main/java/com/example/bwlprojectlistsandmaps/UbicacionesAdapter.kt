package com.example.bwlprojectlistsandmaps

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import com.example.bwlprojectlistsandmaps.Model.Ubicacion
import kotlinx.android.synthetic.main.item_ubicacion.view.*

var color = true

class UbicacionesAdapter(val ubicaciones: List<Ubicacion>, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<UbicacionesAdapter.UbiAdapter>(){




    class UbiAdapter(val view: View): RecyclerView.ViewHolder(view) {


        fun render(ubicacion: Ubicacion){

            view.direccionTitle.text = ubicacion.direccion
            view.latitud.text = ubicacion.latitud
            view.longitud.text = ubicacion.longitud

            if (color){
                view.layoutGeneral.setBackgroundColor(Color.parseColor("#00ffd9"))
                color = false
            }else{
                view.layoutGeneral.setBackgroundColor(Color.parseColor("#00b8d9"))
                color = true
            }

        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UbiAdapter {
        val layoutInflater = LayoutInflater.from(parent.context)

        return UbiAdapter(layoutInflater.inflate(R.layout.item_ubicacion, parent,false))

    }

    override fun onBindViewHolder(holder: UbiAdapter, position: Int) {
        holder.view.setOnClickListener {
            onItemClickListener.onItemClick(ubicaciones[position])
        }
        holder.render(ubicaciones[position])
    }

    override fun getItemCount(): Int = ubicaciones.size

    interface OnItemClickListener{
        fun onItemClick(ubicacion: Ubicacion)
    }
}