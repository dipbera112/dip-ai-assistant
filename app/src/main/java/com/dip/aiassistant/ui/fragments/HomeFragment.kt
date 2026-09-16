package com.dip.aiassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dip.aiassistant.databinding.FragmentHomeBinding
import com.dip.aiassistant.ui.adapters.QuickToolsAdapter
import com.dip.aiassistant.utils.DateTimeUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: QuickToolsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        // Set greeting
        binding.tvGreeting.text = DateTimeUtil.getGreeting()
        binding.tvDate.text = DateTimeUtil.getCurrentDate()

        // Setup quick tools recycler
        adapter = QuickToolsAdapter { tool ->
            onToolClicked(tool)
        }
        binding.rvQuickTools.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@HomeFragment.adapter
        }

        // Load quick tools
        loadQuickTools()
    }

    private fun loadQuickTools() {
        val tools = listOf(
            Pair("🤖 AI Chat", "Chat"),
            Pair("🎤 Voice", "Voice"),
            Pair("📝 Notes", "Notes"),
            Pair("✅ Todo", "Todo"),
            Pair("🧮 Calculator", "Calc"),
            Pair("⚙️ Settings", "Settings")
        )
        adapter.submitList(tools)
    }

    private fun onToolClicked(tool: Pair<String, String>) {
        // Handle tool clicks
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
