package com.merio.footballManager.features.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.merio.footballManager.MainActivity
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.features.splashscreen.SplashScreenViewModel.Status.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.fragment_league_teams.*
import kotlinx.android.synthetic.main.splash_screen_activity.*
import javax.inject.Inject

class SplashScreenActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(SplashScreenViewModel::class.java)

        supportActionBar?.hide()

        mViewModel.statusLiveData.observe(this) { status ->
            progressBarSplashScreen.visibility = View.GONE
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

