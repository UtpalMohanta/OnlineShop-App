package com.example.ecommerceapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.ecommerceapp.Adapter.ItemlistsAdapter
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ViewModel.MainViewModel
import com.example.ecommerceapp.databinding.ActivityListItemBinding

class ListItemActivity : BaseActivity() {

    lateinit var binding: ActivityListItemBinding
    private val viewModel= MainViewModel()

    private var id: String=""
    private var title: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar.visibility= View.VISIBLE
            viewModel.loadItems(id).observe(this@ListItemActivity, Observer {
             listView.layoutManager=
                 LinearLayoutManager(this@ListItemActivity, LinearLayoutManager.VERTICAL,false)
              listView.adapter= ItemlistsAdapter(it)
              progressBar.visibility= View.GONE

            })
            backBtn.setOnClickListener { finish() }
        }
    }

            private fun getBundle(){

                id = intent.getStringExtra("id") ?: " "
                title = intent.getStringExtra("title") ?: " "

                binding.categoryTxt.text=title
            }
}




