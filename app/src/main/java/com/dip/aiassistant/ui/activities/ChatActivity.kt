package com.dip.aiassistant.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dip.aiassistant.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
    }

    private fun setupUI() {
        binding.toolbarChat.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
