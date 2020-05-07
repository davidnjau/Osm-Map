package com.centafrique.openstreetmap

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CoordinatesAdapter(private val context: Context,
                         private val LatitudesList: ArrayList<Float>,
                         private val LongitudesList: ArrayList<Float>)
    : RecyclerView.Adapter<CoordinatesAdapter.CoordinatesViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordinatesAdapter.CoordinatesViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CoordinatesAdapter.CoordinatesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class CoordinatesViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_main, parent, false)){

        init {

        }

        fun AssignValues(txtLat: String, txtLong: String, context: Context){



        }

    }


}