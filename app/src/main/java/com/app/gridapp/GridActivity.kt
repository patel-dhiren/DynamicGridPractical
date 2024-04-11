package com.app.gridapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.gridapp.databinding.ActivityGridBinding

class GridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridBinding
    private lateinit var listAdapter: GridListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var number = intent.getIntExtra("NUM", 0)

        listAdapter = GridListAdapter(this, number * number) {
            AlertDialog.Builder(this)
                .setTitle("FINISH")
                .setMessage("You are Winner")
                .show()
        }
        binding.listItem.layoutManager = GridLayoutManager(this, number)
        binding.listItem.adapter = listAdapter


    }
}