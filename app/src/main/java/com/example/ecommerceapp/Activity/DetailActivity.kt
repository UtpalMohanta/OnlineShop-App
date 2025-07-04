package com.example.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecommerceapp.Adapter.PicListAdapter
import com.example.ecommerceapp.Adapter.SizeAdapter
import com.example.ecommerceapp.Helper.ManagmentCart
import com.example.ecommerceapp.Activity.MainActivity
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {


    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private  var numberOrder=1
    private lateinit var managmentCart: ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)
        getBundle()
        initList()

    }


    /*private fun initList() {
        val colorList = ArrayList<String>()
        if (!item.picUrl.isNullOrEmpty()) {
            colorList.addAll(item.picUrl)

            Glide.with(this)
                .load(colorList[0])
                .into(binding.picMain)

            binding.picList.adapter = PicListAdapter(colorList, binding.picMain)
            binding.picList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
    }*/
    private fun initList() {
        val colorList= ArrayList<String>()
        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.picList.adapter= PicListAdapter(colorList,binding.picMain)
        binding.picList.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)


        val sizeList= ArrayList<String>()
        for (size in item.size)
        {
            sizeList.add(size)
        }
        binding.sizeList.adapter= SizeAdapter(sizeList)
        binding.sizeList.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle() {

        item=(intent.getSerializableExtra("object") as ItemsModel?)!!
        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating}"

        binding.AddTocartBtn.setOnClickListener {
            item.numberInCart=numberOrder
            managmentCart.insertItems(item)
        }

        binding.backBtn.setOnClickListener { finish() }

        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this,
                CartActivity::class.java))
        }
    }
}