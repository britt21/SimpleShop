package com.example.home.views

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.common.NetworkCall
import com.example.data.room.CartData
import com.example.data.room.CartEntity
import com.example.home.databinding.ActivityDetailBinding
import com.example.home.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var productViewModel: ProductViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        productViewModel.readCategory.observe(this, Observer { data ->

            productViewModel.getSingleProducts("${data.category.item}")

        })

        binding.addcartBtn.setOnClickListener {
            addToCart()
            binding.addcartBtn.isEnabled = false
            binding.addcartBtn.text = "Added to Cart"
            Toast.makeText(this, "Item Added to Cart", Toast.LENGTH_SHORT).show()
        }

        binding.backbtn.setOnClickListener {
            onBackPressed()
        }
        getSingleProduct()
        setContentView(binding.root)
    }


    fun getSingleProduct() {
        productViewModel.livesingleProduct.observe(this, Observer { prods ->
            when (prods) {
                is NetworkCall.Loading -> {
                    binding.addcartBtn.isEnabled = false
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkCall.Success -> {
                    binding.addcartBtn.isEnabled = true
                    binding.progressBar.visibility = View.GONE

                    binding.clothImg.load(prods.data?.image)
                    binding.clothTitle.setText("${prods.data?.title}")
                    binding.clothDesc.setText(prods.data?.description)
                    binding.clothPrice.setText("$${prods.data?.price}")
                }
                is NetworkCall.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "${prods.message}", Toast.LENGTH_SHORT).show()
                }


            }
        })
    }

    private fun addToCart() {
        productViewModel.livesingleProduct.observe(this, Observer { prods ->
            var cartData = CartData(
                prods.data!!.image,
                prods.data!!.title,
                prods.data!!.price
            )
            val cartEntity = prods.data?.let {
                CartEntity(
                    0,
                    cartData
                )
            }
            if (cartEntity != null) {
                productViewModel.InsertCart(cartEntity)
            }
        })
    }
}