package com.viveksharma.pokedex_new.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.viveksharma.pokedex_new.database.getDatabase
import com.viveksharma.pokedex_new.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private const val TAG = "PokemonListViewModel"

class PokemonListViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModeScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val pokemonRepository = PokemonRepository(database)

    init {
        viewModeScope.launch {
            pokemonRepository.refreshData()
        }
    }

    val pokemonList = pokemonRepository.pokemonList

    /*private val _pokemonList = MutableLiveData<PokemonListResponse>()
    val pokemonList: LiveData<PokemonListResponse> get() = _pokemonList

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModeScope.launch {
        try {
            val pokemonList = PokemonApiService.PokemonApi.pokemonApi.getPokemonList().await()
            _pokemonList.postValue(pokemonList)

        } catch (networkError: IOException) {
            Log.e(TAG, "refreshDataFromNetwork: ${networkError.localizedMessage}")
        }
    }*/
}