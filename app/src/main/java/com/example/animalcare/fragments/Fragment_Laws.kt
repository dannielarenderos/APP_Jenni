package com.example.animalcare.fragments


import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcare.R
import com.example.animalcare.adapter.LeyAdapter
import com.example.animalcare.database.entities.ley_entity
import com.example.animalcare.viewModels.ViewModelRoom
import com.google.android.material.snackbar.Snackbar


class fragment_leyes : Fragment() {

    private lateinit var viewModel : ViewModelRoom
    private lateinit var adapter : LeyAdapter
    private lateinit var recycler : RecyclerView
    var listenerTool : SearchNewLeyesListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_laws, container, false)
        bind(resources.configuration.orientation,view)
        return view

    }

    interface SearchNewLeyesListener{
        fun manageLandscapeItemClick(ley: ley_entity)
        fun managePortraitItemClick(ley: ley_entity)
    }

    private fun bind(orientation:Int,view:View){
        recycler = view.findViewById(R.id.rv_leyes)
        //btn_search = view.findViewById(com.deushdezt.laboratorio4.R.id.btn_movie)
        if(orientation== Configuration.ORIENTATION_PORTRAIT) {
            adapter = LeyAdapter(ArrayList(),{movie:ley_entity -> listenerTool?.managePortraitItemClick(movie)})
        }
        if(orientation== Configuration.ORIENTATION_LANDSCAPE){
            adapter = LeyAdapter(ArrayList(),{movie:ley_entity-> listenerTool?.manageLandscapeItemClick(movie)})
        }
        viewModel = ViewModelProviders.of(this).get(ViewModelRoom::class.java)
        recycler.apply{
            adapter=this@fragment_leyes.adapter
            layoutManager= LinearLayoutManager(context)
        }
        viewModel.getAllLeyes().observe(this, Observer{
            adapter.updateList(it)
        })


    }

    protected fun isNetworkConnected(): Boolean {
        try {
            val mConnectivityManager =
                activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            return if (mNetworkInfo == null) false else true

        } catch (e: NullPointerException) {
            return false

        }

    }


    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }


}