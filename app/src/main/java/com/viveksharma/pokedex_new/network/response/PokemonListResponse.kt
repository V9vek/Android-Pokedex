package com.viveksharma.pokedex_new.network.response


import com.google.gson.annotations.SerializedName
import com.viveksharma.pokedex_new.database.Pokemon

data class PokemonListResponse(
    @SerializedName("pokemon")
    val pokemonList: List<Pokemon>
)