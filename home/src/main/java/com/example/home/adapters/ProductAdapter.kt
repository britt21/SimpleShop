package com.example.home.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.home.R
import com.example.data.model.product_model.ProductsItem
import com.example.home.views.DetailActivity

class ProductAdapter(val context : Context,val onclick: Onclick) : ListAdapter<ProductsItem,ProductAdapter.ProDuctViewHolder>(Productdiffer) {
    object Productdiffer : DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return  oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return oldItem.id == newItem.id

        }

    }

    class ProDuctViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.product_image)
        val name = view.findViewById<TextView>(R.id.product_name)
        val price = view.findViewById<TextView>(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProDuctViewHolder {
        return ProDuctViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_collection,parent,false))
    }

    override fun onBindViewHolder(holder: ProDuctViewHolder, position: Int) {
        val curItem = getItem(position)
        holder.image.load(curItem.image)
        holder.name.text = curItem.title
        holder.price.text = "$${curItem.price}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
            onclick.onclick(curItem)

        }

    }
}

interface Onclick{

    fun onclick(productsItem: ProductsItem)
}