package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.databinding.RowDialogBinding
import com.aligatorapt.duckdam.view.data.AllComplimentChild
import com.aligatorapt.duckdam.view.data.SelectList

class SelectListAdapter (val context: Context, var items: ArrayList<SelectList>, val isFriendPage: Boolean)
    : RecyclerView.Adapter<SelectListAdapter.MyViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(data: SelectList, position: Int)
        fun OnAddClick(data: SelectList, position: Int)
    }

    var itemClickListener: OnItemClickListener ?= null

    fun setData(newData:ArrayList<SelectList>){
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectListAdapter.MyViewHolder {
        val view = RowDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectListAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            if(isFriendPage && !items[position].isAdded){
                rowAdd.visibility = View.VISIBLE
            }else {
                rowAdd.visibility = View.GONE
            }
            if(position == 0) rowView.visibility = View.GONE
            rowSticker.setImageResource(items[position].sticker)
            rowName.text = items[position].name
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowDialogBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.rowLayout.setOnClickListener{
                itemClickListener?.OnItemClick(items[adapterPosition], adapterPosition)
            }
            binding.rowAdd.setOnClickListener {
                binding.rowAdd.visibility = View.GONE
                itemClickListener?.OnAddClick(items[adapterPosition], adapterPosition)
            }
        }
    }

}