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
    /** (SignUp Step 3)
     * This function is Implementation that Binds Repository With product Interface see (Sign Up Step 4) in product Implementation to See Where
     * Retrofit Makes The Function call directly to the Back end Please Note that these signup do not interact with the backend
     * */
    override suspend fun signupUser(signup: Signup): Response<SignupResponse> {
       return productinterface.signUp(signup)
    }

    /** (Login  Step 3)
     * This function is Implementation that Binds Repository With product Interface see (Login Step 4) in product Implementation to See Where
     * Retrofit Makes The Function call directly to the Back end Please Note that these signup do not interact with the backend
     * */
    override suspend fun loginUser(loginuser: LoginUser): Response<LoginResponse> {
     return   productinterface.loginUser(loginuser)
    }

    /** (GetProduct  Step 3)
     * This Function Triggers The GetProduct from the product Interface (Get Product Step 4)
     * */
    override suspend fun getAllProduct(): Response<Products> {
       return productinterface.getallProducts()
    }

    /** (GetSingleProduct  Step 3)
     * This Function Triggers The GetProduct from the product Interface (GetSingleProduct Step 4)
     * */
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