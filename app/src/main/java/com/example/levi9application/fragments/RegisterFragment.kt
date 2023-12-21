package com.example.levi9application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.levi9application.R
import com.example.levi9application.common.Constants
import com.example.levi9application.databinding.FragmentRegistrationBinding
import com.example.levi9application.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: Fragment(R.layout.fragment_registration) {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    private val emailFilter = Regex(Constants.email_regex)
    private val passwordFilter = Regex(Constants.password_regex)
    private val nameFilter = Regex(Constants.name_regex)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener{
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.loginFragment, true)
                .build()
            findNavController().navigate(action,navOptions)
        }

        binding.btnRegister.setOnClickListener{
            if(filterInput()){
                register()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            else Toast.makeText(
                requireContext(),
                R.string.error_message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun register(){
        val name = binding.registerName.text.toString()
        val password = binding.registerPassword.text.toString()
        val email = binding.registerEmail.text.toString()
        viewModel.putString("${email}${Constants.name_key}",name)
        viewModel.putString("${email}${Constants.email_key}",email)
        viewModel.putString("${email}${Constants.password_key}",password)
        Toast.makeText(requireContext(),R.string.success_message,Toast.LENGTH_SHORT).show()
    }

    private fun filterInput():Boolean{
        val tempEmail = binding.registerEmail.text.trim().toString()
        val tempName = binding.registerName.text.toString()
        val tempPassword = binding.registerPassword.text.toString()
        if(!tempName.matches(nameFilter)){
            return false
        }
        if(!tempEmail.matches(emailFilter)){
            return false
        }
        if(!tempPassword.matches(passwordFilter)){
            return false
        }
        return tempEmail != viewModel.getString("${tempEmail}${Constants.email_key}",null)
    }

    private fun clearPrefs(){
        viewModel.clear()
    }
}