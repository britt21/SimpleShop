package com.example.home.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.model.product_model.ProductsItem
import com.example.data.room.ItemCategory
import com.example.data.room.ProductEntity
import com.example.home.adapters.Onclick
import com.example.home.adapters.ProductAdapter
import com.example.home.databinding.ActivityHomeBinding
import com.example.home.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), Onclick {
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: ProductViewModel
    val productAdapter = ProductAdapter(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


        showShimmer()
        binding.profilebg.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.cartbg.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        binding.shirtbg.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        binding.rvList.adapter = productAdapter
        getProducts()

        setContentView(binding.root)

    }

    fun showShimmer() {
        binding.rvList.showShimmerAdapter()
    }

    fun hideShimmer() {
        binding.rvList.hideShimmerAdapter()
    }

    fun getProducts() {
        viewModel.getallProducts()
        viewModel.liveProduct.observe(this, Observer { product ->
            when (product) {
                is com.example.common.NetworkCall.Success -> {
                    hideShimmer()
                    productAdapter.submitList(product.data)
                }
                is com.example.common.NetworkCall.Error -> {
                    hideShimmer()
                }

                is com.example.common.NetworkCall.Loading -> {
                    showShimmer()
                }
            }
        })
    }

    override fun onclick(productsItem: ProductsItem) {
        var itemCategory = ItemCategory("${productsItem.id}")
        var productId = ProductEntity(1, itemCategory)
        viewModel.insertProductId(productId)
    }

}