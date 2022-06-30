package com.lentatyka.domerwarehouse.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.lentatyka.domerwarehouse.DomerApp
import com.lentatyka.domerwarehouse.R
import com.lentatyka.domerwarehouse.common.Response
import com.lentatyka.domerwarehouse.data.main.background.worker.FirebaseWorker
import com.lentatyka.domerwarehouse.databinding.ActivityLoginBinding
import com.lentatyka.domerwarehouse.di.login.LoginComponent
import com.lentatyka.domerwarehouse.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        loginComponent.viewModelFactory()
    }

    private lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        loginComponent = (application as DomerApp).appComponent.loginComponent().create()
        loginComponent.inject(this)
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(binding.root)
        setViewModel()
    }

    private fun setViewModel() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.response.observe(this) { response ->
            when (response) {
                is Response.Loading -> {
                    binding.loading = true
                }
                is Response.Error -> {
                    binding.loading = false
                    showToast(response.message)
                }
                is Response.Success -> {
                    showToast(getString(R.string.welcome, response.data?.email))
                    startMainActivity()
                }
            }
        }
    }

    private fun startMainActivity() {
        loadDatabase()
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun loadDatabase() {
        WorkManager.getInstance(this).enqueue(
            OneTimeWorkRequestBuilder<FirebaseWorker>().build()
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}