package com.example.ecommerceapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.Activity.DetailActivity
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.databinding.ViewholderItem1Binding
import com.example.ecommerceapp.databinding.ViewholderItem2Binding

class ItemlistsAdapter(val items: MutableList<ItemsModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object{
            const val TYPE_ITEM1=0
            const val TYPE_ITEM2=1
        }
    private var context: Context?=null

    override fun getItemViewType(position: Int): Int {
        return if(position%2==0)TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewHolderItem1(val binding: ViewholderItem1Binding): RecyclerView.ViewHolder(binding.root)
    class ViewHolderItem2(val binding: ViewholderItem2Binding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val ctx = parent.context
        context = ctx

        context=parent.context
        return when(viewType){
            TYPE_ITEM1->{
                val binding= ViewholderItem1Binding.inflate(LayoutInflater.from(context),parent,false)
                ViewHolderItem1(binding)
            }
            TYPE_ITEM2->{
                val binding= ViewholderItem2Binding.inflate(LayoutInflater.from(context),parent,false)
                ViewHolderItem2(binding)
            }
            else -> throw IllegalArgumentException("Invaild view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item=items[position]
        fun bindCommonData(titleText: String,price: String,ratingBar: Float,pic: String,logo: String){
            when(holder)
            {
                is ViewHolderItem1->{

                    holder.binding.titletxt.text=titleText
                    holder.binding.price.text=price
                    holder.binding.ratingBar.rating=ratingBar

                    Glide.with(holder.itemView.context)
                        .load(pic)
                        .into(holder.binding.pic)

                    Glide.with(holder.itemView.context)
                        .load(logo)
                        .into(holder.binding.logoim)

                    holder.itemView.setOnClickListener {
                        val intent= Intent(holder.itemView.context, DetailActivity::class.java)
                        intent.putExtra("object",items[position])
                        holder.itemView.context.startActivity(intent)
                    }
                }
                is ViewHolderItem2->{
                    holder.binding.titletxt.text=titleText
                    holder.binding.price.text=price
                    holder.binding.ratingBar.rating=ratingBar

                    Glide.with(holder.itemView.context)
                        .load(pic)
                        .into(holder.binding.pic)

                    Glide.with(holder.itemView.context)
                        .load(logo)
                        .into(holder.binding.logoim)

                    holder.itemView.setOnClickListener {
                        val intent= Intent(holder.itemView.context, DetailActivity::class.java)
                        intent.putExtra("object",items[position])
                        holder.itemView.context.startActivity(intent)
                    }

                }
            }

        }
        bindCommonData(
            item.title,"${item.price}USD",item.rating.toFloat(),item.picUrl[0],item.logo
        )

    }

    override fun getItemCount(): Int {
        return items.size
    }

}