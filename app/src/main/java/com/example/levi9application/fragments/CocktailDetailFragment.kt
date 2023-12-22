package com.example.levi9application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.levi9application.MainActivity
import com.example.levi9application.R
import com.example.levi9application.databinding.FragmentDetailsBinding
import com.example.levi9application.models.Cocktail
import com.example.levi9application.models.Resource
import com.example.levi9application.viewModels.CocktailViewModel
import com.example.levi9application.viewModels.DetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailFragment : Fragment(R.layout.fragment_details){
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var cocktailViewModel: CocktailViewModel
    private lateinit var detailViewModel: DetailViewModel

    private lateinit var cocktailID: String
    private lateinit var email: String
    private lateinit var cocktail: Cocktail
    private var selected: Boolean = false
    private val args : CocktailDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        email = (activity as MainActivity).email
        cocktailID = args.id ?: ""
        selected = args.selected

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getDetails(cocktailID)
        detailViewModel.getCocktailDetails.observe(viewLifecycleOwner){ cocktails ->
            when(cocktails){
                is Resource.Success -> {
                    binding.group.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Picasso.get().load(cocktails.data.image).into(binding.ivCocktail)
                    binding.tvName.text = cocktails.data.name
                    binding.alcoholicLabel.text =cocktails.data.alcoholic
                    binding.tvSpecificCategory.text =cocktails.data.category
                    binding.tvSpecificGlassType.text =cocktails.data.glass
                    binding.tvSpecificIngredients.text =cocktails.data.string
                    binding.tvSpecificInstructions.text =cocktails.data.instructions

                    cocktail = Cocktail(
                        title =  cocktails.data.name,
                        imageSrc = cocktails.data.image,
                        alcoholic =  cocktails.data.alcoholic,
                        id = cocktails.data.id.toInt(),
                        selected = selected,
                        email = email
                    )
                }
                is Resource.Loading -> {
                    binding.group.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.group.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                }

            }
            detailViewModel.getDetails(cocktailID)
            detailViewModel.checkFavorite(cocktailID,email)

            detailViewModel.favorite.observe(viewLifecycleOwner){favorite ->
                selected = favorite
                if(favorite)  Picasso.get().load(R.drawable.toggle_button_on).into(binding.ivFav)
                else Picasso.get().load(R.drawable.toggle_button_off).into(binding.ivFav)
            }

        }

        binding.ivFav.setOnClickListener{
            if(selected) cocktailViewModel.deleteCocktail(cocktail,email)
            else cocktailViewModel.addCocktail(cocktail,email)
        }
    }


}