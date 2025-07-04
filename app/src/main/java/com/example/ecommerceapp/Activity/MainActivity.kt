package com.example.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.Adapter.BestsSallerAdapter
import com.example.ecommerceapp.Adapter.CategoryAdapter
import com.example.ecommerceapp.ViewModel.MainViewModel
import com.example.ecommerceapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val viewmodel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategories()
        initBestSaller()
        bottomnavigation()
    }

    private fun bottomnavigation() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initBestSaller() {
        binding.progressBarBastSeller.visibility= View.VISIBLE
        viewmodel.bestSeller.observe(this, Observer {
            binding.bestSeller1.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.bestSeller1.adapter = BestsSallerAdapter(it)
            binding.progressBarBastSeller.visibility = View.GONE
        })
    }

    private fun initCategories(){
        binding.progressBarCategories.visibility= View.VISIBLE
        viewmodel.category.observe(this, Observer {
            binding.ViewCategories.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.ViewCategories.adapter = CategoryAdapter(it)
            binding.progressBarCategories.visibility = View.GONE
        })
    }
}