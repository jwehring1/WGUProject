package com.example.wguproject.ui.pokemonlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wguproject.data.PokemonApiService
import com.example.wguproject.data.response.pokemonlist.PokemonList
import kotlinx.coroutines.*
import java.io.IOException

class PokemonListViewModel : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + CoroutineName("PokemonListViewModel"))

    private val _pokemonList = MutableLiveData<List<PokemonList>>()
    val pokemonList: LiveData<List<PokemonList>>
        get() = _pokemonList

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        scope.launch(Dispatchers.IO) {
            try {
                //Calls the api service and posts the response if available
                val apiService = PokemonApiService()
                val currentPokemonResponse = apiService.getPokemonList().await()
                _pokemonList.postValue(currentPokemonResponse.results)
            } catch (e: Exception) {
                Log.ERROR
            }
        }
    }
}