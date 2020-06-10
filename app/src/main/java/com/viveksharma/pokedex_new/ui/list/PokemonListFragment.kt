package com.viveksharma.pokedex_new.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.viveksharma.pokedex_new.R
import com.viveksharma.pokedex_new.databinding.FragmentPokemonListBinding
import com.viveksharma.pokedex_new.ui.adapters.PokemonItemClick
import com.viveksharma.pokedex_new.ui.adapters.PokemonRVAdapter

class PokemonListFragment : Fragment() {

    private lateinit var pokemonRVAdapter: PokemonRVAdapter

    private val viewModel: PokemonListViewModel by lazy {
        val activity = requireActivity()
        ViewModelProvider(this,
            PokemonListViewModelFactory(
                activity.application
            )
        ).get(PokemonListViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            pokemonRVAdapter.pokemonList = it
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPokemonListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_list, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        pokemonRVAdapter = PokemonRVAdapter(PokemonItemClick {
            //When a Pokemon Item will be clicked, this block will run

            //Go to Detail Fragment, while passing id
            this.findNavController().navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(it.id))
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonRVAdapter
        }

        return binding.root
    }

}