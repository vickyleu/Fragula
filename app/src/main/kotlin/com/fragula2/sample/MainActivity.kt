package com.fragula2.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fragula2.sample.bottomnav.BottomNavActivity
import com.fragula2.sample.databinding.ActivityMainBinding
import com.fragula2.sample.simple.BlankActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.simpleFlow.setOnClickListener {
            Intent(this, BlankActivity::class.java).run {
                startActivity(this)
            }
        }
        binding.bottomnavFlow.setOnClickListener {
            Intent(this, BottomNavActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}