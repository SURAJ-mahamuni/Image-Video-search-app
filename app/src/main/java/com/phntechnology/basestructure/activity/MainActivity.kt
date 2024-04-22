package com.phntechnology.basestructure.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.commonutils.util.hideView
import com.example.commonutils.util.showView
import com.phntechnology.basestructure.R
import com.phntechnology.basestructure.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeNavController()

        fragmentDestinationHandle()

        setUI()

        initializeListener()

    }

    private fun initializeNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }

    private fun fragmentDestinationHandle() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashScreenFragment -> {
                    binding.bottomNavigation.hideView()
                }

                R.id.videoSearchFragment -> {
                    binding.bottomNavigation.showView()
                }
                R.id.searchFragment -> {
                    binding.bottomNavigation.showView()
                }
                else -> {
                    binding.bottomNavigation.hideView()
                }
            }
        }
    }

    private fun initializeListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_image_search -> {
                    if (binding.bottomNavigation.menu.findItem(binding.bottomNavigation.selectedItemId) != item) {
                        navController.navigate(R.id.searchFragment)
                    }
                    true
                }

                R.id.menu_video_search -> {
                    if (binding.bottomNavigation.menu.findItem(binding.bottomNavigation.selectedItemId) != item) {
                        navController.navigate(R.id.videoSearchFragment)
                    }
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun setUI() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
        WindowInsetsControllerCompat(window, binding.root).isAppearanceLightStatusBars = true
    }
}