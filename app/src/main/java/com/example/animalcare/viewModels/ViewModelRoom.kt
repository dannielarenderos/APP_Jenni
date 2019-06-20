package com.example.animalcare.viewModels

import android.app.Application
import androidx.lifecycle.viewModelScope
import android.util.Log
import androidx.lifecycle.*
import com.example.animalcare.database.RoomDB
import com.example.animalcare.database.entities.ley_entity
import com.example.animalcare.database.entities.org_entity
import com.example.animalcare.repositories.LeyRepository
import com.example.animalcare.repositories.Repository
import com.example.animalcare.service.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRoom(context: Application) : AndroidViewModel(context) {
    private val leyRepository: LeyRepository
    var insertedId: MutableLiveData<Long> = MutableLiveData(0)
    val listaLeyes: MutableLiveData<MutableList<ley_entity>> = MutableLiveData(emptyList<ley_entity>().toMutableList())
    init {
        val leyDao = RoomDB.getInstance(context, viewModelScope).leyDao()
        //val orgDao= RoomDB.getInstance(context).orgDao()
        //repository= Repository(orgDao, leyDao)
        leyRepository = LeyRepository(leyDao)
    }


    fun getLeyBy(ley: String): LiveData<ley_entity> {
        return leyRepository.getLey(ley)
    }

    fun getAllLeyes() = viewModelScope.launch(Dispatchers.IO) {
        val response = leyRepository.retrieveLeyes().await()
        if(response.isSuccessful){
            if(response.code()==200){
                listaLeyes.postValue(response.body()?.toMutableList())
            }
        }
    }
}



