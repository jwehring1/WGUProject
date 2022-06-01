package com.example.wguproject.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wguproject.ui.pokemon.PokemonViewModel

class PokemonViewFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PokemonViewModel::class.java)){
            return PokemonViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}