package com.app.gridapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.gridapp.databinding.ItemGridLayoutBinding
import java.util.Random


class GridListAdapter(
    private val context: Context,
    private val gridSize: Int,
    private val onClick: () -> Unit
) : RecyclerView.Adapter<GridListAdapter.MyViewHolder>() {

    private val selectedPositions = mutableSetOf<Int>()
    private val random = Random()
    private var colorIndex = 0
    private var nextSelected = -1
    private var selectedColor = getBackgroundColor(colorIndex)

    init {
        nextSelected = random.nextInt(gridSize - 1)
        selectedPositions.add(nextSelected)
    }

    class MyViewHolder(var binding: ItemGridLayoutBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemGridLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val isSelected = selectedPositions.contains(position)

        if (isSelected) {
            holder.binding.cardItem.setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    selectedColor
                )
            )
        }

        holder.binding.cardItem.setOnClickListener {
            if (position == nextSelected) {
                val remainingIndices = (0 until gridSize).filter { it !in selectedPositions }

                if (remainingIndices.isNotEmpty()) {
                    nextSelected = remainingIndices[random.nextInt(remainingIndices.size)]
                    selectedPositions.add(nextSelected)
                    notifyItemChanged(nextSelected)
                } else {
                    Log.d("LAST", "onBindViewHolder: Last Item Clicked")
                    //Toast.makeText(context, "Last item clicked", Toast.LENGTH_SHORT).show()
                    colorIndex++
                    if (colorIndex <= 2){
                        selectedColor = getBackgroundColor(colorIndex)
                        selectedPositions.clear()
                        nextSelected = random.nextInt(gridSize - 1)
                        selectedPositions.add(nextSelected)
                        notifyItemChanged(nextSelected)
                    }else{
                        onClick()
                    }
                }
                //Toast.makeText(context, "${remainingIndices.size}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount() = gridSize

    fun getBackgroundColor(num: Int): Int {
        return when (num) {
            0 -> R.color.green
            1 -> R.color.red
            2 -> R.color.blue
            else -> R.color.white
        }
    }

}
