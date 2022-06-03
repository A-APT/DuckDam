package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.RowFriendlistBinding
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.viewModel.FriendViewModel

class FriendListAdapter(
    val context: Context,
    var items: ArrayList<UserResponseDto>,
    val viewModel: FriendViewModel?,
    val user: UserResponseDto?
) : RecyclerView.Adapter<FriendListAdapter.MyViewHolder>(){

    var isFriendPage: Boolean = false

    interface OnItemClickListener{
        fun OnItemClick(data: UserResponseDto, position: Int)
        fun OnAddClick(data: UserResponseDto, position: Int)
    }

    var itemClickListener: OnItemClickListener ?= null

    fun setData(newData: ArrayList<UserResponseDto>, _isFriendPage: Boolean){
        items.clear()
        items.addAll(newData)
        isFriendPage = _isFriendPage
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowFriendlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            if(!isFriendPage){
                if (viewModel != null) {
                    if(viewModel.friends.value != null && viewModel.friends.value!!.contains(items[position])) {
                        rowAdd.visibility = View.GONE
                    } else if(items[position] == user){
                        rowAdd.visibility = View.GONE
                    } else {
                        rowAdd.visibility = View.VISIBLE
                    }
                }
            }else rowAdd.visibility = View.GONE

            if(items[position].profile != null){
                val decodedImageBytes: ByteArray = Base64.decode(items[position].profile, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.size)
                rowSticker.setImageBitmap(bitmap)
            }else {
                rowSticker.setImageResource(R.drawable.small_sample)
            }
            rowName.text = items[position].name

        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowFriendlistBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rowLayout.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition], adapterPosition)
            }
            binding.rowAdd.setOnClickListener {
                binding.rowAdd.visibility = View.GONE
                itemClickListener?.OnAddClick(items[adapterPosition], adapterPosition)
            }
        }
    }
}