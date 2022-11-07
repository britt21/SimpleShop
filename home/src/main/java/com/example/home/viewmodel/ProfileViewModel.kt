package com.example.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.NetworkCall
import com.example.data.model.update_user.UpdateUser
import com.example.data.model.update_user.response.UpdateUserResponse
import com.example.network.service.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private val _updateUser = MutableLiveData<NetworkCall<UpdateUserResponse>>()
    val updateUser: LiveData<NetworkCall<UpdateUserResponse>>
        get() = _updateUser

    fun updateUser(updateUser: UpdateUser) {
        _updateUser.value = NetworkCall.Loading()
        viewModelScope.launch {
            try {
                val response = productRepository.updateUser(updateUser)

                when {
                    response.isSuccessful -> {
                        _updateUser.value =
                            com.example.common.NetworkCall.Success(response.body()!!)
                    }

                    response.code() == 404 || response.code() == 400 || response.code() == 403 || response.code() == 500 || response.code() == 503 -> {
                        _updateUser.value =
                            NetworkCall.Error("An Error Occured")
                    }


                }

            } catch (e: Exception) {
                _updateUser.value = NetworkCall.Error("No Internet Connection")

            }
        }
    }

}