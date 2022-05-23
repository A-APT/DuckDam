package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.RowHomeStickerBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto

class HomeStickerAdapter (var items:ArrayList<ComplimentResponseDto>, val context: Context)
    : RecyclerView.Adapter<HomeStickerAdapter.MyViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view: View, data: ComplimentResponseDto, position: Int)
    }

    var itemClickListener:OnItemClickListener?= null

    inner class MyViewHolder(val binding: RowHomeStickerBinding): RecyclerView.ViewHolder(binding.root) {
        init{
            binding.sticker.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    fun setData(newData:ArrayList<ComplimentResponseDto>){
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    fun getItem(position:Int): ComplimentResponseDto{
        return items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowHomeStickerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            sticker.setImageResource(context.resources.obtainTypedArray(R.array.stickerImg).getResourceId(items[position].stickerNum, 0))
        }
    }
}
