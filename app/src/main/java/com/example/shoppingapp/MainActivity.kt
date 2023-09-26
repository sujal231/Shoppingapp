package com.example.shoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnadd.setOnClickListener {
            val intent =  Intent(this,AddActivity::class.java )
            startActivity(intent)
        }
        binding.btnshow.setOnClickListener {
            val intent =  Intent(this,ShowActivity::class.java )
            startActivity(intent)
        }
    }
}