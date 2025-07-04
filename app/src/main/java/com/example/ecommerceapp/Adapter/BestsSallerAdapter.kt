package com.example.ecommerceapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.Activity.DetailActivity
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.databinding.ViewholderBestSellerBinding

class BestsSallerAdapter(val items: MutableList<ItemsModel>):
    RecyclerView.Adapter<BestsSallerAdapter.ViewHolder>() {

   inner class ViewHolder(val binding: ViewholderBestSellerBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestsSallerAdapter.ViewHolder {
      val binding: ViewholderBestSellerBinding = ViewholderBestSellerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestsSallerAdapter.ViewHolder, position: Int) {
        holder.binding.titletxt.text=items[position].title
        holder.binding.price.text=items[position].price.toString()+" USD"
        holder.binding.ratingBar.rating=items[position].rating.toFloat()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        Glide.with(holder.itemView.context)
            .load(items[position].logo)
            .into(holder.binding.logoim)


        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}