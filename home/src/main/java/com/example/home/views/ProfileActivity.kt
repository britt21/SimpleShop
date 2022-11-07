package com.example.home.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.NetworkCall
import com.example.data.model.update_user.Address
import com.example.data.model.update_user.Geolocation
import com.example.data.model.update_user.Name
import com.example.data.model.update_user.UpdateUser
import com.example.home.databinding.ActivityProfileBinding
import com.example.home.viewmodel.ProductViewModel
import com.example.home.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    lateinit var viewModel: ProfileViewModel

    lateinit var binding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityProfileBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        binding.cartbg.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        binding.uname.doOnTextChanged{ text, start, before, count ->
            binding.savebtn.visibility = View.VISIBLE
        }
        binding.homeBg.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.savebtn.setOnClickListener {
            updateUser()
        }


        setContentView(binding.root)

    }

    fun updateUser(){
      var email =   binding.uemail.text.toString()
     var name =    binding.name.text.toString()
      var phone =   binding.uphone.text.toString()


        val geoLocation = Geolocation("","")
        val dname = Name(name,"")
        var adress = Address("",geoLocation,2,",","")
        val updateUser = UpdateUser(adress,email, dname,"",phone,"")
        viewModel.updateUser(updateUser)
        viewModel.updateUser.observe(this, Observer { user ->
            when(user){
                is NetworkCall.Success ->{
                    Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                    onBackPressed()
                }

                is NetworkCall.Error ->{
                    Toast.makeText(this,"${user.message}",Toast.LENGTH_SHORT).show()

                }
                is NetworkCall.Loading ->{

                    binding.progressBar.visibility = View.VISIBLE
                }
            }


        })
    }
}