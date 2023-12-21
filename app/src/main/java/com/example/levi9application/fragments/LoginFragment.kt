package com.example.levi9application.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.levi9application.MainActivity
import com.example.levi9application.R
import com.example.levi9application.databinding.FragmentLoginBinding
import com.example.levi9application.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var email: String
    private lateinit var viewModel:AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener{
            if(checkInput()){
                if(login()){
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.putExtra("_email",email)
                    startActivity(intent)
                    activity?.finish()
                }
                else{
                    Toast.makeText(requireContext(),"Something ain't right",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun checkInput() : Boolean{
        if(binding.loginEmail.text.isEmpty()) return false
        return !binding.loginPassword.text.isNullOrEmpty()
    }

    private fun login() : Boolean{
        email = viewModel.getString("${binding.loginEmail.text}_email",null)
        val password = viewModel.getString("${email}_password", null)
        return !(email.isEmpty() || password.isEmpty())
    }
}