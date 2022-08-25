package com.emanuel.pulsusnorris.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.emanuel.pulsusnorris.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
    }

    private fun setupToolbar() = binding.run {
        val navHostFragment = supportFragmentManager.findFragmentById(navHostMain.id) as NavHostFragment
        val navController = navHostFragment.navController
        val appToolbarConfig = AppBarConfiguration(navGraph = navController.graph)

        mainToolbar.setupWithNavController(
            navController = navController,
            configuration = appToolbarConfig
        )
    }
}