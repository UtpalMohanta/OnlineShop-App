package com.example.ecommerceapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.Adapter.CartAdapter
import com.example.ecommerceapp.Helper.ChangeNumberItemsListener
import com.example.ecommerceapp.Helper.ManagmentCart
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart

    private var tax: Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)
        setVariable()
        initCartList()
        calculaeCart()

    }

    private fun initCartList() {

        binding.cartView.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter= CartAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculaeCart()
            }

        })
    }

    private fun calculaeCart() {
        val percentTxt=0.02
        val delivery=15.0
        tax= Math.round((managmentCart.getTotalFee()*percentTxt)*100)/100.0
        val total= Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal= Math.round(managmentCart.getTotalFee()*100)/100


        with(binding) {
            totalFeeTax.text="$${itemTotal}"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text="$$total"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}