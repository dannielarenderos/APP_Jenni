package com.example.animalcare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcare.R
import com.example.animalcare.database.entities.ley_entity
import kotlinx.android.synthetic.main.cardview_leyes.view.*

class LeyAdapter (var leyes:List<ley_entity>,val clickListener: (ley_entity)-> Unit): RecyclerView.Adapter<LeyAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int):ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_leyes,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount():Int{
        return leyes.size
    }

    override fun onBindViewHolder(holder: ViewHolder,position:Int){
        holder.bind(leyes[position],clickListener)
    }

    fun updateList(newLey : List<ley_entity>){
        newLey.forEach{
            println("Nueva ley")
        }
        this.leyes=newLey
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(ley: ley_entity,clickListener: (ley_entity) -> Unit) = with(itemView){
            this.tv_apartado.text=ley.apartado
            this.tv_articulo.text=ley.articulo
            this.setOnClickListener{clickListener(ley)}
        }
    }
}