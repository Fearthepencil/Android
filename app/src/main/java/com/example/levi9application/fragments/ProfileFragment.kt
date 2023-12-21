package com.example.levi9application.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levi9application.AuthActivity
import com.example.levi9application.MainActivity
import com.example.levi9application.R
import com.example.levi9application.databinding.FragmentProfileBinding
import com.example.levi9application.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = (activity as MainActivity).email
        binding.nameText.setText(
            viewModel.getString("${email}_name",resources.getString(R.string.name))
        )
        binding.emailText.setText(
            viewModel.getString("${email}_email",resources.getString(R.string.email))
        )
        binding.passwordText.setText(
            viewModel.getString("${email}_password",resources.getString(R.string.password))
        )

        binding.logoutButton.setOnClickListener {
            startActivity(Intent(requireContext(), AuthActivity::class.java))
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}