package com.example.auth.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.auth.databinding.ActivitySignUpBinding
import com.example.auth.views.viewModel.AuthViewModel
import com.example.common.observeOnce
import com.example.data.model.auth_model.Address
import com.example.data.model.auth_model.Geolocation
import com.example.data.model.auth_model.Name
import com.example.data.model.auth_model.Signup
import com.example.home.views.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    lateinit var binding :ActivitySignUpBinding
    lateinit var viewModel : AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)


        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


        binding.signupBtn.setOnClickListener {
            val name = binding.nameit.text
            val check = binding.checkBox
            val uname = Name(name.toString(), "")
            val email = binding.nameit.text
            val password = binding.nameit.text


            val geolocation = Geolocation("1000", "1000")
            val address = Address("Lagos", geolocation, 99, "Lagos", "1000")
            val signup = Signup(address, email.toString(), uname, password.toString(), "08181020222", "-user")
            if ( validate()) {
                binding.signupBtn.isEnabled = false
                binding.progressBar.visibility = View.VISIBLE

                viewModel.signUpUser(signup)

                viewModel.liveSignUp.observe(this, Observer { data ->
                    when (data) {
                        is com.example.common.NetworkCall.Success -> {
                            finish()
                            binding.signupBtn.isEnabled = true
                            binding.progressBar.visibility = View.GONE
                            val stintent = Intent(this, HomeActivity::class.java)
                            startActivity(stintent)
                        }
                        is com.example.common.NetworkCall.Error -> {
                            binding.signupBtn.isEnabled = true
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "${data.message}", Toast.LENGTH_SHORT).show()
                        }

                        is com.example.common.NetworkCall.Loading -> {
                            binding.signupBtn.isEnabled = false
                            binding.progressBar.visibility = View.VISIBLE
                        }

                    }
                })


            } else {
                Toast.makeText(this, "Fields Must Not Be Empty", Toast.LENGTH_SHORT).show()
                binding.signupBtn.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }

            binding.lgintxt.setOnClickListener {
             val logintent = Intent(this,LoginActivity::class.java)
                startActivity(logintent)
            }


        }
        setContentView(binding.root)
    }

    fun validate() : Boolean{
        val name = binding.nameit.text
        val check = binding.checkBox
        val email = binding.nameit.text
        val password = binding.nameit.text

        if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()&&password.length > 4 && check.isChecked == true){
            return true
        }
        if (password.length < 4){
            Toast.makeText(this,"Password Must be more than 4 characters",Toast.LENGTH_SHORT).show()
            return false
        }
        return if (check.isChecked == false){
            Toast.makeText(this,"You must Accept the terms and conditions to continue",Toast.LENGTH_SHORT).show()
            false
        } else{
            Toast.makeText(this,"Please Check all fields are correct",Toast.LENGTH_SHORT).show()
            false
        }
    }
}
