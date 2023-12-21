package com.example.levi9application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.levi9application.R
import com.example.levi9application.databinding.FragmentRegistrationBinding
import com.example.levi9application.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: Fragment(R.layout.fragment_registration) {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    private val emailFilter = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}")
    private val passwordFilter = Regex("^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{6,}\$")
    private val nameFilter = Regex("^[a-zA-Z]+$")


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
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener{
            if(filterInput()){
                register()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            else Toast.makeText(
                requireContext(),
                "Something ain't right",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun register(){
        val name = binding.registerName.text.toString()
        val password = binding.registerPassword.text.toString()
        val email = binding.registerEmail.text.toString()
        viewModel.putString("${email}_name",name)
        viewModel.putString("${email}_email",email)
        viewModel.putString("${email}_password",password)
        Toast.makeText(requireContext(),"saved in shared prefs: $name$email$password",Toast.LENGTH_SHORT).show()
    }

    private fun filterInput():Boolean{
        val tempEmail = binding.registerEmail.text.trim().toString()
        if(!binding.registerName.text.matches(nameFilter)){
            return false
        }
        if(tempEmail.matches(emailFilter)){
            return false
        }
        if(binding.registerPassword.text.matches(passwordFilter)){
            return false
        }
        return tempEmail != viewModel.getString("${tempEmail}_email",null)
    }

    private fun clearPrefs(){
        viewModel.clear()
    }
}