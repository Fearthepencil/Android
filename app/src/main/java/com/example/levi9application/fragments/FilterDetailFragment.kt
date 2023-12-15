package com.example.levi9application.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.levi9application.R
import com.example.levi9application.adapters.FilterDetailAdapter
import com.example.levi9application.databinding.FragmentFilterDetailsBinding
import com.example.levi9application.models.Category
import com.example.levi9application.models.Resource
import com.example.levi9application.viewModels.FilterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterDetailFragment : Fragment(R.layout.fragment_filter_details) {
    private val args: FilterDetailFragmentArgs by navArgs()
    private var _binding: FragmentFilterDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FilterDetailAdapter
    private lateinit var list : MutableList<Category>
    private lateinit var viewModel: FilterDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FilterDetailViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text : String = " " + args.selected
        val text2 : String = binding.labelTextFilterDetail.text.toString()
        binding.labelTextFilterDetail.text = text2 + text
        val arg: String = when(args.selected){
            "Alcoholic or not" -> "a"
            "Category" -> "c"
            "Glass used" -> "g"
            "Ingredient" -> "i"
            else -> ""
        }
        viewModel.getCategories(arg)
        viewModel.getCategoryList.observe(viewLifecycleOwner) {categories ->
            when(categories){
                is Resource.Success -> {

                    list = categories.data.toMutableList()
                    if(list.isEmpty()){
                        showDialog("Greska","Greska")
                        binding.rViewFilters.visibility = View.GONE
                        binding.indeterminateBarFilter.visibility = View.GONE
                    }
                    else {
                        binding.indeterminateBarFilter.visibility = View.GONE
                        binding.rViewFilters.visibility = View.VISIBLE
                    }
                    rvSetup()

                }
                is Resource.Loading -> {
                    list = mutableListOf()
                    rvSetup()
                    binding.indeterminateBarFilter.visibility = View.VISIBLE
                    binding.rViewFilters.visibility = View.GONE
                }
                is Resource.Error -> {
                    list = mutableListOf()
                    binding.rViewFilters.visibility = View.GONE
                    binding.indeterminateBarFilter.visibility = View.GONE
                }
            }
        }


    }

    private fun rvSetup() {
        adapter = FilterDetailAdapter(list, viewBindingOnItemClickListener)
        binding.rViewFilters.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.rViewFilters.layoutManager = layoutManager
    }
    private fun showDialog(message: String, title: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage(message).setTitle(title).setPositiveButton("OK") { _, _ ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private val viewBindingOnItemClickListener = object : FilterDetailAdapter.OnItemClickListener {
        override fun onItemClick(category: Category) {

        }
    }
}

