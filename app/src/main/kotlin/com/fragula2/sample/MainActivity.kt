package com.fragula2.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fragula2.animation.SwipeController
import com.fragula2.sample.databinding.ActivityMainBinding
import com.fragula2.utils.findSwipeController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var swipeController: SwipeController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.navHost.getFragment<NavHostFragment>().also {
            swipeController = it.findSwipeController()
            navController = it.navController
        }

        // Animate arrow icon
        DrawerArrowDrawable(this@MainActivity).also { arrow ->
            binding.toolbar.navigationIcon = arrow.apply {
                color = ContextCompat.getColor(this@MainActivity, android.R.color.black)
            }
            binding.toolbar.setNavigationOnClickListener {
                when (arrow.progress) {
                    0f -> binding.drawerLayout.openDrawer(GravityCompat.START)
                    1f -> navController.popBackStack()
                }
            }
            swipeController.addOnSwipeListener { position, positionOffset, _ ->
                arrow.progress = if (position >= 1) 1f else positionOffset
            }
        }

        // Can't use `setupWithNavController()` extension, multibackstack is not supported
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            val bundle = bundleOf("label" to menuItem.title)
            when (menuItem.itemId) {
                R.id.favorites -> navController.navigate(R.id.tabFragment, bundle)
                R.id.friends -> navController.navigate(R.id.tabFragment, bundle)
                R.id.likes -> navController.navigate(R.id.tabFragment, bundle)
                R.id.settings -> navController.navigate(R.id.tabFragment, bundle)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}