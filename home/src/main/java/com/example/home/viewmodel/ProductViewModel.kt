package com.example.home.viewmodel

import androidx.lifecycle.*
import com.example.data.model.cart_model.Carts
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.single_product.SingleProduct
import com.example.data.model.update_user.UpdateUser
import com.example.data.model.update_user.response.UpdateUserResponse
import com.example.data.room.AllProductEntity
import com.example.data.room.CartData
import com.example.data.room.CartEntity
import com.example.data.room.ItemCategory
import com.example.data.room.ProductDao
import com.example.data.room.ProductEntity
import com.example.network.service.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,

) : ViewModel() {


    var readCategory: LiveData<ProductEntity> = productRepository.ReadCategory()
    var readCart: LiveData<List<CartEntity>> = productRepository.getAllCart()




    private val _livesingleProduct =
        MutableLiveData<com.example.common.NetworkCall<SingleProduct>>()
    val livesingleProduct: LiveData<com.example.common.NetworkCall<SingleProduct>>
        get() = _livesingleProduct


    private val _livecart = MutableLiveData<com.example.common.NetworkCall<Carts>>()
    val livecart: LiveData<com.example.common.NetworkCall<Carts>>
        get() = _livecart




    val _liveProduct = MutableLiveData<com.example.common.NetworkCall<Products>>()
    val liveProduct: LiveData<com.example.common.NetworkCall<Products>>
        get() = _liveProduct

    fun getallProducts() {
        _liveProduct.value = com.example.common.NetworkCall.Loading()
        viewModelScope.launch {
            try {
                val response = productRepository.getAllProduct()

                when {
                    response.isSuccessful -> {
                        _liveProduct.value =
                            com.example.common.NetworkCall.Success(response.body()!!)

                        println("DATA_FROM::LDATA: "+_liveProduct.value?.data)

                        var productbody = response.body()
                        if (productbody != null) {
                            var products = AllProductEntity(2, productbody)

                            insertAllProduct(products)
                        }


                    }

                    response.code() == 404 || response.code() == 400 || response.code() == 403 || response.code() == 500 || response.code() == 503 -> {
                        _liveProduct.value =
                            com.example.common.NetworkCall.Error("An Error Occured")
                    }


                }

            } catch (e: Exception) {
                _liveProduct.value = com.example.common.NetworkCall.Error("No Internet Connection")

            }
        }
    }


    // Room
    fun insertProductId(productEntity: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.InsertCategory(productEntity)
        }
    }

    fun insertAllProduct(allProductEntity: AllProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertAllProducts(allProductEntity)

        }
    }

        var liveOfflinePRoduct: MutableLiveData<List<AllProductEntity>> = MutableLiveData()

        fun readOfflineProducts(): LiveData<List<AllProductEntity>> {
            return productRepository.getallProducts()
        }


        fun InsertCart(cartEntity: CartEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                productRepository.addToCart(cartEntity)

            }
        }



        fun getSingleProducts(id: String) {
            _livesingleProduct.value = com.example.common.NetworkCall.Loading()
            viewModelScope.launch {
                try {
                    val response = productRepository.getSingleProduct(id)
                    when {
                        response.isSuccessful -> {
                            _livesingleProduct.value =
                                com.example.common.NetworkCall.Success(response.body()!!)
                        }

                        response.code() == 404 || response.code() == 400 || response.code() == 403 || response.code() == 500 || response.code() == 503 -> {
                            _livesingleProduct.value =
                                com.example.common.NetworkCall.Error("An Error Occured")
                        }


                    }

                } catch (e: Exception) {
                    _livesingleProduct.value =
                        com.example.common.NetworkCall.Error("No Internet Connection")

                }
            }
        }


    }
