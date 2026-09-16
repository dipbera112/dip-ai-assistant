package com.dip.aiassistant.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dip.aiassistant.databinding.FragmentChatBinding
import com.dip.aiassistant.ui.activities.ChatActivity
import com.dip.aiassistant.ui.adapters.MessageAdapter
import com.dip.aiassistant.ui.viewmodels.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
    }

    private fun setupUI() {
        adapter = MessageAdapter()
        binding.rvMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ChatFragment.adapter
        }

        binding.btnOpenChat.setOnClickListener {
            startActivity(Intent(requireContext(), ChatActivity::class.java))
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.allMessages.collect { messages ->
                adapter.submitList(messages.reversed())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
