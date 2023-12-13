package com.example.levi9application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.levi9application.databinding.FragmentFilterDetailsBinding

class FilterDetailFragment : Fragment(R.layout.fragment_filter_details) {
    private val args: FilterDetailFragmentArgs by navArgs()
    private var _binding: FragmentFilterDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,args.selected, Toast.LENGTH_SHORT).show()
    }
}