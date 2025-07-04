package com.example.ecommerceapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.Helper.ChangeNumberItemsListener
import com.example.ecommerceapp.Helper.ManagmentCart
import com.example.ecommerceapp.Model.ItemsModel
import com.example.ecommerceapp.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null): RecyclerView.Adapter<CartAdapter.Viewholder>(){
    inner class Viewholder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)


    private val managmentCart= ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
     val binding= ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
       val item=listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="${item.price}"
        holder.binding.totalEachItem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text=item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.picCart)


        holder.binding.plusCartBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected,position,object : ChangeNumberItemsListener{

                override fun onChanged() {
                  notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
        holder.binding.minusCartBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected,position,object : ChangeNumberItemsListener{

                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }
    }

    override fun getItemCount(): Int {
      return listItemSelected.size
    }
}