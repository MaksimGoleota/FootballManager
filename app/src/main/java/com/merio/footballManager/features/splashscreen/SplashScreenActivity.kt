package com.merio.footballManager.features.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.merio.footballManager.MainActivity
import com.merio.footballManager.R
import com.merio.footballManager.databinding.SplashScreenActivityBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.features.splashscreen.SplashScreenViewModel.Status.*
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashScreenActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SplashScreenActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(SplashScreenViewModel::class.java)

        supportActionBar?.hide()

        mViewModel.statusLiveData.observe(this) { status ->
            binding.progressBarSplashScreen.visibility = View.GONE
            when (status) {
                Completed -> {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Error -> {
                    Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

