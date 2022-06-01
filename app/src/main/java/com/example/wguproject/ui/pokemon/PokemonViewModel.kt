package com.example.wguproject.ui.pokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wguproject.data.PokemonApiService
import com.example.wguproject.data.response.pokemon.Ability
import kotlinx.coroutines.*

class PokemonViewModel() : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + CoroutineName("PokemonViewModel"))

    private val _pokemon = MutableLiveData<List<Ability>>()
    val pokemon: LiveData<List<Ability>>
        get() = _pokemon

    public fun fetchPokemon(url: String) {
        scope.launch(Dispatchers.IO) {
            try {
                //Calls the api service and posts the response if available
                val apiService = PokemonApiService()
                val currentPokemonResponse = apiService.getPokemon(url).await()
                _pokemon.postValue(currentPokemonResponse.abilities)
            } catch (e: Exception) {
                Log.ERROR
            }
        }
    }
}