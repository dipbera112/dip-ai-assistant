package com.dip.aiassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dip.aiassistant.databinding.FragmentToolsBinding
import com.dip.aiassistant.ui.adapters.ToolsAdapter
import com.dip.aiassistant.ui.viewmodels.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToolsFragment : Fragment() {

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var adapter: ToolsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
    }

    private fun setupUI() {
        adapter = ToolsAdapter { tool ->
            onToolSelected(tool)
        }
        binding.rvTools.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@ToolsFragment.adapter
        }

        loadTools()
    }

    private fun loadTools() {
        val tools = listOf(
            Triple("🧮", "Calculator", "calc"),
            Triple("📝", "Notes", "notes"),
            Triple("✅", "Todo", "todo"),
            Triple("⏰", "Reminders", "reminder"),
            Triple("📅", "Calendar", "calendar"),
            Triple("🌤️", "Weather", "weather"),
            Triple("📰", "News", "news"),
            Triple("📱", "Device Info", "device"),
            Triple("🔋", "Battery", "battery"),
            Triple("🔦", "Flashlight", "flashlight"),
            Triple("📷", "Camera", "camera"),
            Triple("📠", "QR Scanner", "qr")
        )
        adapter.submitList(tools)
    }

    private fun onToolSelected(tool: Triple<String, String, String>) {
        // Handle tool selection
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
