package com.example.animalcare.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.animalcare.R
import com.example.animalcare.viewModels.ViewModelRoom

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val leyesViewModel = ViewModelProviders.of(this).get(ViewModelRoom::class.java)
        leyesViewModel.getAllLeyes()
        leyesViewModel.listaLeyes.observe(this, Observer{lista->
           // adapter.changeList(lista)
            for(ley in lista){
                Log.v("ListaLeyes", ley.articulo)
            }
        })
    }
}
