package com.example.wguproject.data.response.pokemonlist


data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonList>
)