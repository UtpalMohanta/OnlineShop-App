package com.example.ecommerceapp.Model

import android.icu.text.CaseMap.Title
import java.io.FileDescriptor
import java.io.Serializable

data class ItemsModel (

    var title:String="",
    var description:String="",
    var logo:String="",
    var categoryId:String="",
    var picUrl:ArrayList<String> =ArrayList(),
    var size:ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating:Double=0.0,
    var numberInCart:Int=0
):Serializable
