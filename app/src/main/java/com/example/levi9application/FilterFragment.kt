package com.example.levi9application

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.levi9application.databinding.FragmentFilterBinding
import com.example.levi9application.databinding.ItemFilterBinding

class FilterFragment: Fragment(R.layout.fragment_filter){

    private val options = arrayOf("Alcoholic/Non-Alcoholic","Category","Glass used","Ingredient")

    private var _binding : FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = object: ArrayAdapter<String>(
            this.requireContext(),
            0,
            options
        ){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemFilterBinding: ItemFilterBinding =
                    if(convertView == null){
                        ItemFilterBinding.inflate(
                            LayoutInflater.from(context),
                            parent,
                            false
                        )
                    }else{
                        ItemFilterBinding.bind(convertView)
                    }
                itemFilterBinding.filterTitle.text = getItem(position)
                return itemFilterBinding.root
            }
        }
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = options[position]
            Log.e("selected",selectedItem)
            // Do something with the selected item
        }
    }


}