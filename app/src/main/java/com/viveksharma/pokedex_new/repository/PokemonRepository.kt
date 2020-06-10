package com.viveksharma.pokedex_new.repository

import androidx.lifecycle.LiveData
import com.viveksharma.pokedex_new.database.Pokemon
import com.viveksharma.pokedex_new.database.PokemonDatabase
import com.viveksharma.pokedex_new.network.PokemonApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(private val database: PokemonDatabase) {

    val pokemonList: LiveData<List<Pokemon>> = database.pokemonDao.getPokemonList()

    /*suspend fun getPokemonById(pokemonId: Int): LiveData<Pokemon> {
        return withContext(Dispatchers.IO) {
            return@withContext database.pokemonDao.getPokemonDetails(pokemonId)
        }
    }*/

    fun getPokemonById(pokemonId: Int): LiveData<Pokemon>{
        return database.pokemonDao.getPokemonDetails(pokemonId)
    }

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val pokemonListResponse =
                PokemonApiService.PokemonApi.pokemonApi.getPokemonList().await()
            database.pokemonDao.insert(pokemonListResponse.pokemonList)
        }
    }
}