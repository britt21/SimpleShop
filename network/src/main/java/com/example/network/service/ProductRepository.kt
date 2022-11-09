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
import com.example.data.room.ProductEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response



interface ProductRepository {

    //Network
    /** (SignUp Step 2)
     * This function is Sent to AuthViewModel (Step 1), To see where the interaction happens with the Interface and the endpoint
     * Base Url Check For Repository Impl (SignUp Step 3)
     * */
    suspend fun signupUser(signup: Signup) :Response<SignupResponse>

    /** (Login Step 2)
     * This function is Sent to AuthViewModel (Login Step 1), To see where the interaction happens with the Interface and the endpoint
     * Base Url Check For Repository Impl (Login Step 3)
     * */
    suspend fun loginUser(loginuser: LoginUser) :Response<LoginResponse>

    /** (GetProduct  Step 2)
     * This Function Triggers The GetProduct from the repository (GetProduct Step 3) in product Repository Impl (Step 3)
     * */
    suspend fun getAllProduct() :Response<Products>

    /** (GetSingleProduct  Step 2)
     * This Function Triggers The GetProduct from the repository (GetSingleProduct Step 3) in product Repository Impl (Step 3)
     * */
    suspend fun getSingleProduct(id : String) :Response<SingleProduct>

    suspend fun updateUser(updateUser:  UpdateUser) :Response<UpdateUserResponse>


    //Room
    fun getAllCart() :LiveData<List<CartEntity>>
    suspend fun addToCart(id : CartEntity)

    suspend fun InsertCategory(productEntity: ProductEntity)
    fun ReadCategory(): LiveData<ProductEntity>


}