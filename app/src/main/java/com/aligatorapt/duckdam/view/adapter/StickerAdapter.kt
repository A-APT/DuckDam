package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.RowStickerBinding

class StickerAdapter(
    val context: Context,
    var items: ArrayList<Boolean>,
    var stringArray: Array<String>
)
    : RecyclerView.Adapter<StickerAdapter.MyViewHolder>(){

    fun setData(newData:ArrayList<Boolean>){
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerAdapter.MyViewHolder {
        val view = RowStickerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: StickerAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            if(items[position]){
                rowStickerSticker.setImageResource(context.resources.obtainTypedArray(R.array.stickerImg).getResourceId(position, 0))
                rowStickerName.text = stringArray[position]
            }else{
                rowStickerSticker.setImageResource(R.drawable.small_sample)
                rowStickerSticker.setPadding(12)
                rowStickerName.text = "???"
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowStickerBinding): RecyclerView.ViewHolder(binding.root){

    }
}