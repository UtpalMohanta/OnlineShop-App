package com.example.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ViewholderPicListBinding


class PicListAdapter(val items: MutableList<String>,var picMain: ImageView):
    RecyclerView.Adapter<PicListAdapter.ViewHolder>() {


    private var selectPosition=-1
    private var lastselectPosition=-1
    private lateinit var context: Context


   inner class ViewHolder(val binding: ViewholderPicListBinding ):
       RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicListAdapter.ViewHolder {
       context=parent.context
        val binding= ViewholderPicListBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicListAdapter.ViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position])
            .into(holder.binding.picList)

        holder.binding.root.setOnClickListener {
            lastselectPosition=selectPosition
            selectPosition=position
            notifyItemChanged(lastselectPosition)
            notifyItemChanged(selectPosition)

            Glide.with(context)
                    .load(items[position])
                    .into(picMain)

        }

    }

    override fun getItemCount(): Int=items.size

}