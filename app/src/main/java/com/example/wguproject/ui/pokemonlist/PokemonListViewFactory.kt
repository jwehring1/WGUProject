package com.example.wguproject.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PokemonListViewFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PokemonListViewModel::class.java)){
            return PokemonListViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}