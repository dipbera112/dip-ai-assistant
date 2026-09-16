package com.dip.aiassistant.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dip.aiassistant.databinding.FragmentProfileBinding
import com.dip.aiassistant.ui.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.btnEditProfile.setOnClickListener {
            showEditProfileDialog()
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun showEditProfileDialog() {
        // Show edit profile dialog
    }

    private fun logout() {
        // Handle logout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
