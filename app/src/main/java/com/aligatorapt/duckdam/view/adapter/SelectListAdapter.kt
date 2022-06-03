package com.aligatorapt.duckdam.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.RowDialogBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.dto.user.UserResponseDto

class SelectListAdapter (
    val context: Context,
    var items: ArrayList<UserResponseDto>
    ) : RecyclerView.Adapter<SelectListAdapter.MyViewHolder>(){

    private var isSticker: Boolean = false
    interface OnItemClickListener{
        fun OnItemClick(data: UserResponseDto, position: Int, isSticker: Boolean)
    }

    var itemClickListener: OnItemClickListener ?= null

    fun setData(newData:ArrayList<UserResponseDto>, _isSticker: Boolean){
        items.clear()
        items.addAll(newData)
        isSticker = _isSticker
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectListAdapter.MyViewHolder {
        val view = RowDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: SelectListAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            if(position == 0) rowView.visibility = View.GONE

            if(!isSticker){
                if(items[position].profile != null){
                    val decodedImageBytes: ByteArray = Base64.decode(items[position].profile, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.size)
                    rowSticker.setImageBitmap(bitmap)
                }else {
                    rowSticker.setImageResource(R.drawable.small_sample)
                }
                rowName.text = items[position].name
            }else{
                rowSticker.setImageResource(context.resources.obtainTypedArray(R.array.stickerImg).getResourceId(items[position].profile!!.toInt(), 0))
                rowName.text = items[position].name
            }

            rowLayout.setOnClickListener{
                itemClickListener?.OnItemClick(items[position], position, isSticker)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MyViewHolder(val binding: RowDialogBinding): RecyclerView.ViewHolder(binding.root){
    }

}