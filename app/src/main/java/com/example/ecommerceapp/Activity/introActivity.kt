package com.example.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.Activity.MainActivity
import com.example.ecommerceapp.databinding.ActivityIntroBinding

class introActivity : BaseActivity() {

    lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startBtn.setOnClickListener { startActivity(Intent(this@introActivity,MainActivity::class.java)) }
            }
        }
    }
