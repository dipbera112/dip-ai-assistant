package com.dip.aiassistant.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dip.aiassistant.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
    }

    private fun setupUI() {
        binding.toolbarProfile.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
