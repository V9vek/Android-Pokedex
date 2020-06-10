package com.viveksharma.pokedex_new.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.viveksharma.pokedex_new.R
import com.viveksharma.pokedex_new.database.Pokemon
import com.viveksharma.pokedex_new.databinding.FragmentPokemonDetailBinding
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*

private const val TAG = "PokemonDetailFragment"

class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonDetailViewModel by lazy {
        val activity = requireActivity()
        val args = arguments?.let { PokemonDetailFragmentArgs.fromBundle(it) }
        val pokemonId = args?.pokemonId

        ViewModelProvider(
            this,
            PokemonDetailViewModelFactory(
                activity.application, pokemonId!!
            )
        ).get(PokemonDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPokemonDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_detail, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val args = arguments?.let { PokemonDetailFragmentArgs.fromBundle(it) }
        val pokemonId = args?.pokemonId

        val pokemonData = viewModel.getPokemonData(pokemonId!!)

        pokemonData.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            layoutLoading.visibility = View.GONE
            layoutAbout.visibility = View.VISIBLE
            updateBackButton()
            updatePokemonDetails(it)
        })

        return binding.root
    }

    private fun updateBackButton() {
        ivBackButton.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }

    private fun updatePokemonDetails(pokemon: Pokemon) {
        tvNum.text = "#${pokemon.num}"
        tvName.text = pokemon.name
        Glide.with(this.requireContext())
            .load(pokemon.img)
            .into(ivImage)
        tvHeight.text = pokemon.height
        tvWeight.text = pokemon.weight
        tvSpawnTime.text = pokemon.spawnTime
        tvType.text = pokemon.type.joinToString(separator = ", ")
        tvWeakness.text = pokemon.weaknesses.joinToString(separator = ", ")

        setPrevEvolution(tvPrev, tvPrevEvolution, pokemon)
        setNextEvolution(tvNext, tvNextEvolution, pokemon)
    }

    private fun setPrevEvolution(leftView: TextView, rightView: TextView, pokemon: Pokemon) {
        if (!pokemon.prevEvolution.isNullOrEmpty()) {
            leftView.visibility = View.VISIBLE
            val evList: MutableList<String> = mutableListOf()
            for (listValue in pokemon.prevEvolution) {
                evList.add(listValue.name)
            }
            rightView.text = evList.joinToString(separator = ", ")
        } else
            leftView.visibility = View.GONE
    }

    private fun setNextEvolution(leftView: TextView, rightView: TextView, pokemon: Pokemon) {
        if (!pokemon.nextEvolution.isNullOrEmpty()) {
            leftView.visibility = View.VISIBLE
            val evList: MutableList<String> = mutableListOf()
            for (listValue in pokemon.nextEvolution) {
                evList.add(listValue.name)
            }
            rightView.text = evList.joinToString(separator = ", ")
        } else
            leftView.visibility = View.GONE
    }

}