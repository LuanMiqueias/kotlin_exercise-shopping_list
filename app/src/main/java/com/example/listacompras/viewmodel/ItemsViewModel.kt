package com.example.listacompras.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listacompras.data.ItemEntity
import com.example.listacompras.data.ItemsDatabase
import com.example.listacompras.data.toModel
import com.example.listacompras.model.ItemModel
import com.example.listacompras.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val database: ItemsDatabase
): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }

    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    fun addItem(name:String){
       viewModelScope.launch(Dispatchers.IO) {
           val entity = ItemEntity(id = 0, name=name);

           database.itemsDao().insert(entity);
           fetchAll()
       }
    }

   private fun removeItem(item: ItemModel){
       viewModelScope.launch(Dispatchers.IO) {
           val entity = item.toEntity()

           database.itemsDao().delete(entity);
           fetchAll()
       }
    }

    private suspend fun fetchAll(){
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }
        itemsLiveData.postValue(result)
    }
}