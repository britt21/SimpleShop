package com.example.home.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.data.room.CartData
import com.example.home.R

class CartAdapter : ListAdapter<CartData,CartAdapter.CartViewHolder>(Productdiffer) {
    object Productdiffer : DiffUtil.ItemCallback<CartData>(){
        override fun areItemsTheSame(oldItem: CartData, newItem: CartData): Boolean {
            return  oldItem.cartImg === newItem.cartImg
        }

        override fun areContentsTheSame(oldItem: CartData, newItem: CartData): Boolean {
            return oldItem.cartImg == newItem.cartImg

        }

    }

    class CartViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.crtimg)
        val name = view.findViewById<TextView>(R.id.crt_title)
        val price = view.findViewById<TextView>(R.id.cart_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val curItem = getItem(position)
        holder.image.load(curItem.cartImg)
        holder.name.text = "${curItem.carttitle}"
        holder.price.text = "$${curItem.cartprice}"


    }
}