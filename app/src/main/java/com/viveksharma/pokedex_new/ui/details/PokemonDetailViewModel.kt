package com.viveksharma.pokedex_new.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viveksharma.pokedex_new.database.Pokemon
import com.viveksharma.pokedex_new.database.getDatabase
import com.viveksharma.pokedex_new.repository.PokemonRepository
import kotlinx.coroutines.*

class PokemonDetailViewModel(application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val pokemonRepository = PokemonRepository(database)


    private val _pokemonData = MutableLiveData<Pokemon>()
    val pokemonData: LiveData<Pokemon> get() = _pokemonData


    fun getPokemonData(pokemonId: Int): LiveData<Pokemon> {
        return pokemonRepository.getPokemonById(pokemonId)
    }

}