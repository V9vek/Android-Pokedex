package com.viveksharma.pokedex_new.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.viveksharma.pokedex_new.network.response.PokemonListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonApiService {

    @GET("pokedex.json")
    fun getPokemonList(): Deferred<PokemonListResponse>

    object PokemonApi {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonApi = retrofit.create(PokemonApiService::class.java)
    }
}