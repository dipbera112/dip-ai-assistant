package com.dip.aiassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dip.aiassistant.databinding.FragmentMemoryBinding
import com.dip.aiassistant.ui.adapters.MemoryAdapter
import com.dip.aiassistant.ui.viewmodels.MemoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemoryFragment : Fragment() {

    private var _binding: FragmentMemoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MemoryViewModel by viewModels()
    private lateinit var adapter: MemoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
        observeState()
    }

    private fun setupUI() {
        adapter = MemoryAdapter { memory ->
            viewModel.deleteMemory(memory)
        }
        binding.rvMemories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MemoryFragment.adapter
        }

        binding.btnAddMemory.setOnClickListener {
            showAddMemoryDialog()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.allMemories.collect { memories ->
                adapter.submitList(memories)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                // Handle UI state
            }
        }
    }

    private fun showAddMemoryDialog() {
        // Show dialog to add memory
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
