package com.centafrique.openstreetmap

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoordinatesAdapter(private val context: Context,
                         private val LatitudesList: ArrayList<Double>,
                         private val LongitudesList: ArrayList<Double>)
    : RecyclerView.Adapter<CoordinatesAdapter.CoordinatesViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordinatesAdapter.CoordinatesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return CoordinatesViewHolder(inflater, parent)
    }

    override fun getItemCount() = LatitudesList.size

    override fun onBindViewHolder(holder: CoordinatesAdapter.CoordinatesViewHolder, position: Int) {

        val txtLat : String = LatitudesList[position].toString()
        val txtLong : String = LongitudesList[position].toString()

        holder.AssignValues(txtLat, txtLong, context)
    }

    class CoordinatesViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.coordinates, parent, false)){

        private var tvLat : TextView? = null
        private var tvLong : TextView? = null

        init {

            tvLat = itemView.findViewById(R.id.tvLat)
            tvLong = itemView.findViewById(R.id.tvLong)


        }

        fun AssignValues(txtLat: String, txtLong: String, context: Context){

            tvLat?.text = txtLat
            tvLong?.text = txtLong

            itemView.setOnClickListener {


            }

        }

    }


}