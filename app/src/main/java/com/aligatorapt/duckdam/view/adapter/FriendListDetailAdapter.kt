package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.databinding.RowFriendlistdetailBinding
import com.aligatorapt.duckdam.view.data.AllComplimentChild

class FriendListDetailAdapter(val context: Context, var items: ArrayList<AllComplimentChild>)
    :RecyclerView.Adapter<FriendListDetailAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendListDetailAdapter.MyViewHolder {
        val view = RowFriendlistdetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendListDetailAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            rowFldetailSticker.setImageResource(items[position].sticker)
            rowFldetailDate.text = items[position].date
            rowFldetailContent.text = items[position].content
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowFriendlistdetailBinding): RecyclerView.ViewHolder(binding.root){

    }

}