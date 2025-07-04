package com.example.ecommerceapp.Repository

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.Model.CategoryModel
import com.example.ecommerceapp.Model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {

    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun loadCategory():LiveData<MutableList<CategoryModel>>{
        val categoryLiveData=MutableLiveData<MutableList<CategoryModel>>()
        val ref=firebaseDatabase.getReference("Category")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val lists= mutableListOf<CategoryModel>()

               for (childSnapshot in snapshot.children)
               {
                   val list =childSnapshot.getValue(CategoryModel::class.java)
                   if (list!=null){
                       lists.add(list)
                   }
               }
                categoryLiveData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return categoryLiveData
    }
    fun loadBestSeller():LiveData<MutableList<ItemsModel>> {
        val bestSellerLiveData=MutableLiveData<MutableList<ItemsModel>> ()
        val ref=firebaseDatabase.getReference(("BestSeller"))

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){

                    val list=childSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null)
                    {
                        lists.add(list)
                    }
                }
                bestSellerLiveData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return bestSellerLiveData
    }

    fun loadItems(categoryId: String):LiveData<MutableList<ItemsModel>> {
        val itemsLiveData=MutableLiveData<MutableList<ItemsModel>> ()
        val ref=firebaseDatabase.getReference(("Items"))

        val query: Query=ref.orderByChild("categoryId").equalTo(categoryId)

      query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){

                    val list=childSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null)
                    {
                        lists.add(list)
                    }
                }
                itemsLiveData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return itemsLiveData
    }

}