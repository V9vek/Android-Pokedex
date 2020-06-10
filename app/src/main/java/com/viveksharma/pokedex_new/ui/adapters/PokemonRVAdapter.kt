package com.viveksharma.pokedex_new.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.viveksharma.pokedex_new.R
import com.viveksharma.pokedex_new.databinding.LayoutPokemonlistItemBinding
import com.viveksharma.pokedex_new.database.Pokemon

class PokemonRVAdapter(
    val callback: PokemonItemClick
) : RecyclerView.Adapter<PokemonRVAdapter.PokemonViewHolder>() {

    var pokemonList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val withDataBinding: LayoutPokemonlistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PokemonViewHolder.LAYOUT,
            parent,
            false
        )
        return PokemonViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.pokemon = pokemonList[position]
            it.pokemonItemCallBack = callback
        }
    }

    class PokemonViewHolder(val viewDataBinding: LayoutPokemonlistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.layout_pokemonlist_item
        }
    }
}

class PokemonItemClick(val block: (Pokemon) -> Unit) {
    fun onClick(pokemon: Pokemon) = block(pokemon)
}