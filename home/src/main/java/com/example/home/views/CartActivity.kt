package com.example.home.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.room.CartData
import com.example.home.adapters.CartAdapter
import com.example.home.databinding.ActivityCartBinding
import com.example.home.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    lateinit var viewModel : ProductViewModel
    val carts = ArrayList<CartData>()
    val cartAdapter = CartAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        binding.etback.setOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        viewModel.readCart.observe(this, Observer { cart ->
            for (items in cart){
                carts.add(items.cartData)
                cartAdapter.submitList(carts)

            }
        })


        binding.rvcart.adapter = cartAdapter

        setContentView(binding.root)

    }
}