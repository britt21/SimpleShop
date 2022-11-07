package com.example.network.service

import androidx.lifecycle.LiveData
import com.example.data.model.auth_model.Signup
import com.example.data.model.auth_model.login.LoginUser
import com.example.data.model.auth_model.response.LoginResponse
import com.example.data.model.auth_model.response.SignupResponse
import com.example.data.model.cart_model.Carts
import com.example.data.model.cart_model.add_cart.AddCart
import com.example.data.model.cart_model.add_cart.response.AddCartResponse
import com.example.data.model.product_model.Products
import com.example.data.model.product_model.single_product.SingleProduct
import com.example.data.model.update_user.UpdateUser
import com.example.data.model.update_user.response.UpdateUserResponse
import com.example.data.room.CartEntity
import com.example.data.room.ProductDao
import com.example.data.room.ProductEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ProductRepoImpl(val productinterface: ProductInterface,val productDao: ProductDao) :
    ProductRepository {

    // Network
    override suspend fun signupUser(signup: Signup): Response<SignupResponse> {
       return productinterface.signUp(signup)
    }

    override suspend fun loginUser(loginuser: LoginUser): Response<LoginResponse> {
     return   productinterface.loginUser(loginuser)
    }

    override suspend fun getAllProduct(): Response<Products> {
       return productinterface.getallProducts()
    }

    override suspend fun getSingleProduct(id : String): Response<SingleProduct> {
        return productinterface.getSingleProduct(id)
    }

    override suspend fun updateUser(updateUser: UpdateUser): Response<UpdateUserResponse> {
       return productinterface.updateUser(updateUser)
    }






    //Room
    override fun getAllCart(): LiveData<List<CartEntity>> {
        return productDao.ReadCart()
    }


    override suspend fun addToCart(id: CartEntity) {
        productDao.InsertCart(id)
    }

    override suspend fun InsertCategory(productEntity: ProductEntity) {
        return productDao.InsertCategory(productEntity)
    }


    override fun ReadCategory(): LiveData<ProductEntity> {
        return productDao.ReadCategory()
    }

}