package com.example.ecommerceapp.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Looper
import android.provider.Telephony.Mms.Intents
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.Activity.ListItemActivity
import com.example.ecommerceapp.Model.CategoryModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ViewholderCategoryBinding
import com.google.firebase.database.Transaction
import java.util.logging.Handler

class CategoryAdapter(val items:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding:ViewholderCategoryBinding)
        :RecyclerView.ViewHolder(binding.root)

    private var selectPosition =-1
    private var lastSelectPosition=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val item =items[position]
        holder.binding.titleCat.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.picCat)

       //teach korar por bg green and pic ta white hobe...
        if(selectPosition==position){
            holder.binding.picCat.setBackgroundResource(R.drawable.green_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.picCat,
                ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,R.color.white))
            )

        //teach na korla por bg gray and pic ta black hobe...
        }else{
            holder.binding.picCat.setBackgroundResource(R.drawable.gray_rag_bng)
            ImageViewCompat.setImageTintList(
                holder.binding.picCat,
                ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context,R.color.black))
            )
        }
        holder.binding.root.setOnClickListener{
            if(position!=RecyclerView.NO_POSITION){
                lastSelectPosition=selectPosition
                selectPosition=position
                notifyItemChanged(lastSelectPosition)
                notifyItemChanged(selectPosition)
            }

            // akta thake ae aktay jate
            android.os.Handler(Looper.getMainLooper()).postDelayed({

              val intent= Intent(holder.itemView.context,ListItemActivity::class.java).apply {
                  putExtra("id",item.id.toString())
                  putExtra("title",item.title)
              }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            },500)
        }
    }


}