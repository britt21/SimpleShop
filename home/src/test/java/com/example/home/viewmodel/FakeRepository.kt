package com.example.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.data.model.auth_model.Signup
import com.example.data.model.auth_model.login.LoginUser
import com.example.data.model.auth_model.response.LoginResponse
import com.example.data.model.auth_model.response.SignupResponse
import com.example.data.model.cart_model.Product
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.single_product.Rating
import com.example.data.model.product_model.single_product.SingleProduct
import com.example.data.model.update_user.UpdateUser
import com.example.data.model.update_user.response.Address
import com.example.data.model.update_user.response.Geolocation
import com.example.data.model.update_user.response.Name
import com.example.data.model.update_user.response.UpdateUserResponse
import com.example.data.room.AllProductEntity
import com.example.data.room.CartEntity
import com.example.data.room.ProductEntity
import com.example.network.service.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response



var signupResponse = SignupResponse(0)
var loginResponse = LoginResponse("")
var product = Products()


val productEntitymock = mutableListOf<ProductEntity>()
val allproductEntitymock = mutableListOf<AllProductEntity>()
val cartEntitymock = mutableListOf<CartEntity>()

class FakeRepository() : ProductRepository {
    var productEntity = MutableLiveData<ProductEntity>()
    var allproductEntity : LiveData<List<AllProductEntity>> = MutableLiveData()

    override suspend fun signupUser(signup: Signup): Response<SignupResponse> {
        return Response.success(signupResponse)
    }

    override suspend fun loginUser(loginuser: LoginUser): Response<LoginResponse> {
        return Response.success(loginResponse)
    }

    override suspend fun getAllProduct(): Response<Products> {
        return Response.success(product)
    }

    override suspend fun getSingleProduct(id: String): Response<SingleProduct> {
        return Response.success(SingleProduct("","",0,"",0.0, Rating(0,0.0),""))
    }

    override suspend fun updateUser(updateUser: UpdateUser): Response<UpdateUserResponse> {
        return Response.success(UpdateUserResponse(Address("", Geolocation("",""),
        2,"",""),"", Name("",""),"","",""))


    }

    override fun getAllCart(): LiveData<List<CartEntity>> {
        return  flowOf<List<CartEntity>>().asLiveData()
    }

    override suspend fun addToCart(id: CartEntity) {
        cartEntitymock.add(id)
    }

    override suspend fun InsertCategory(productEntity: ProductEntity) {
        productEntitymock.add(productEntity)
    }

    override fun ReadCategory(): LiveData<ProductEntity> {
        return productEntity
    }

    override suspend fun insertAllProducts(productEntity: AllProductEntity) {
        allproductEntitymock.add(productEntity)
    }

    override fun getallProducts(): LiveData<List<AllProductEntity>> {
        return  allproductEntity
    }

    override fun getProducts(): LiveData<List<CartEntity>> {
        return  flowOf<List<CartEntity>>().asLiveData()
    }

}