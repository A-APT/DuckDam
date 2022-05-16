package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.RowStickerBinding
import com.aligatorapt.duckdam.view.data.SelectList




class StickerAdapter(val context: Context, var items: ArrayList<SelectList>)
    : RecyclerView.Adapter<StickerAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerAdapter.MyViewHolder {
        val view = RowStickerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: StickerAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            if(items[position].isAdded){
                rowStickerSticker.setImageResource(items[position].sticker)
                rowStickerName.text = items[position].name
            }else{
                rowStickerSticker.setImageResource(R.drawable.small_sample)
                rowStickerSticker.setPadding(10)
                rowStickerName.text = "???"
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowStickerBinding): RecyclerView.ViewHolder(binding.root){

    }
}