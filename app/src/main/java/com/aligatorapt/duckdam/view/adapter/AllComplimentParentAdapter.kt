package com.aligatorapt.duckdam.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligatorapt.duckdam.databinding.RowAllComplimentParentBinding
import com.aligatorapt.duckdam.view.activity.DuckdamDetailActivity
import com.aligatorapt.duckdam.view.data.AllComplimentChild
import com.aligatorapt.duckdam.view.data.AllComplimentParent

class AllComplimentParentAdapter (val context: Context, var items:ArrayList<AllComplimentParent>)
    : RecyclerView.Adapter<AllComplimentParentAdapter.MyViewHolder>(){

    inner class MyViewHolder(val binding: RowAllComplimentParentBinding): RecyclerView.ViewHolder(binding.root) {
        init{

        }
    }

    fun setData(newData:ArrayList<AllComplimentParent>){
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowAllComplimentParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            date.text = items[position].date

            //자식 어뎁터 설정

            val childAdapter = AllComplimentChildAdapter(items[position].sticker)
            childAdapter.itemClickListener = object : AllComplimentChildAdapter.OnItemClickListener {
                override fun OnItemClick(
                    holder: AllComplimentChildAdapter.MyViewHolder,
                    view: View,
                    data: AllComplimentChild,
                    position: Int
                ) {
                    val intent = Intent(context, DuckdamDetailActivity::class.java)
                    intent.putExtra("item", data)
                    context.startActivity(intent)
                }
            }
            childRecyclerView.adapter = childAdapter
        }
    }
}
