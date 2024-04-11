package com.app.gridapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.gridapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {

            var number = binding.etNumber.text.toString().trim().toInt()

            var intent = Intent(this, GridActivity::class.java)
            intent.putExtra("NUM", number)
            startActivity(intent)


        }

    }
}