package com.merio.footballManager

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val graph = navController.navInflater.inflate(R.navigation.navigation)
        navController.graph = graph
    }
}
