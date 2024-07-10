package com.hfad.dailyquotes.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.databinding.ActivityMainBinding
import com.hfad.dailyquotes.fragments.QuotesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        val navController = navHostFragment?.findNavController()

        binding.bottomNav.setupWithNavController(navController!!)
    }


    public fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE
    }

    public fun showBottomNav() {
        binding.bottomNav.visibility = View.VISIBLE
    }
}