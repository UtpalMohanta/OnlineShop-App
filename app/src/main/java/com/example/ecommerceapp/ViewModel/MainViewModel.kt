package com.example.ecommerceapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.Model.CategoryModel
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.Repository.MainRepository

class MainViewModel:ViewModel(){

    private val repository=MainRepository()

    val category:LiveData<MutableList<CategoryModel>> =repository.loadCategory()

    val bestSeller:LiveData<MutableList<ItemsModel>> =repository.loadBestSeller()


    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>>{

        return repository.loadItems(categoryId)
    }
}