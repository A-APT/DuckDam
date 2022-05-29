package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.RowFriendlistdetailBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.view.data.AllComplimentChild
import java.text.SimpleDateFormat

class FriendListDetailAdapter(val context: Context, var items: ArrayList<ComplimentResponseDto>)
    :RecyclerView.Adapter<FriendListDetailAdapter.MyViewHolder>(){

    fun setData(newData:ArrayList<ComplimentResponseDto>){
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendListDetailAdapter.MyViewHolder {
        val view = RowFriendlistdetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendListDetailAdapter.MyViewHolder, position: Int) {
        holder.binding.apply { rowFldetailSticker.setImageResource(context.resources.obtainTypedArray(R.array.stickerImg).getResourceId(items[position].stickerNum, 0))
            val dateFormat = SimpleDateFormat("yyyy.MM.dd")
            rowFldetailDate.text = dateFormat.format(items[position].date)
            rowFldetailContent.text = items[position].message
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowFriendlistdetailBinding): RecyclerView.ViewHolder(binding.root){

    }

}