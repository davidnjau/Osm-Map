package com.centafrique.openstreetmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView


    private lateinit var coordinatesAdapter: CoordinatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val LatitudesList = arrayListOf<Double>(
            -1.563813,
            -1.549261,
            -1.620816,
            0.444393,
            1.685609,
            2.036985,
            -0.873928,
            0.609181
        )

        val LongitudesList = arrayListOf<Double>(
            36.841117,
            36.751319,
            34.354268,
            35.749396,
            37.858565,
            36.166675,
            35.419622,
            35.760164
        )

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)

//        coordinatesAdapter = CoordinatesAdapter(this, LatitudesList, LongitudesList)
//
//        recyclerView.adapter = coordinatesAdapter


    }
}
