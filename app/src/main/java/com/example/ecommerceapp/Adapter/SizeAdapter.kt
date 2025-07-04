package com.example.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ViewholderSizeBinding


class SizeAdapter(val items: MutableList<String>): RecyclerView.Adapter<SizeAdapter.Viewholoder>() {

    private var selectPosition=-1
    private var lastSelectPosition=-1
    private lateinit var context: Context
    inner class Viewholoder(val binding: ViewholderSizeBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholoder {
         context=parent.context
    val binding= ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholoder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.Viewholoder, position: Int) {
        holder.binding.sizeTxt.text=items[position]
        holder.binding.root.setOnClickListener {

            lastSelectPosition=selectPosition
            selectPosition=position
            notifyItemChanged(lastSelectPosition)
            notifyItemChanged(selectPosition)

        }
        if(selectPosition==position)
        {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.green_bg)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.gray_rag_bng)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
}