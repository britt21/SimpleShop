package com.example.auth.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.auth.databinding.ActivityLoginBinding
import com.example.auth.views.viewModel.AuthViewModel
import com.example.common.observeOnce
import com.example.data.model.auth_model.login.LoginUser
import com.example.home.views.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * This is Login Backend Holding the Ui and Handles The functionalities that the users Sees
 * */
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    lateinit var binding : ActivityLoginBinding
    lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        /** (Step 1)
         * This is the Button Onclick Listiner that triggers the Login Function
         * */
        binding.loginBtn.setOnClickListener {
            loginUser()
        }


        binding.suptxt.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        setContentView(binding.root)
    }

    /** (Step 2)
     * This Function Automatically triggers the Login directly from the view-Model when the Login Button Is Clicked
     *
     * */
    fun loginUser(){
        val email = binding.nameit.text
        val password = binding.passit.text

        if (email.isNotEmpty() && password.isNotEmpty()) {

            binding.progressBar.visibility = View.VISIBLE
            binding.loginBtn.isEnabled=false
            val loginUser = LoginUser(password.toString(), email.toString())
            viewModel.loginUser(loginUser)
            viewModel.livelogin.observe(this, Observer { data ->

                when(data) {
                    is  com.example.common.NetworkCall.Success ->{
                        finish()
                        binding.loginBtn.isEnabled = true
                        binding.progressBar.visibility = View.GONE

                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    is com.example.common.NetworkCall.Error ->{
                        binding.loginBtn.isEnabled = true
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this,"${data.message}",Toast.LENGTH_SHORT).show()
                    }
                    is com.example.common.NetworkCall.Loading ->{
                        binding.loginBtn.isEnabled=false
                    }

                }
            })


        }else{
            Toast.makeText(this,"Check all fields are not Empty",Toast.LENGTH_SHORT).show()

        }
    }
}