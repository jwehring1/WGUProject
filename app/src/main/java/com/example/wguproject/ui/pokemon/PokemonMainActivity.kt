package com.example.wguproject.ui.pokemon

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wguproject.R
import com.example.wguproject.ui.pokemonlist.PokemonListRecyclerViewAdapter

class PokemonMainActivity: AppCompatActivity() {
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_main)
        //Initializing the pokemonviewfactory and viewmodel
        val factory = PokemonViewFactory()
        viewModel = ViewModelProviders.of(this,factory).get(PokemonViewModel::class.java)

        //Grabbing the url from the pokemon
        val url:String = intent.getStringExtra("pokemonUrl").toString()
        viewModel.fetchPokemon(url)

        //Creating the recyclerview
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = PokemonRecyclerViewAdapter()
        recyclerview.adapter = adapter

        //Waits for fetchPokemon coroutine in the viewModel to run and then fills in the recyclerview
        viewModel.pokemon.observe(this, Observer { it ->
            adapter.submitList(it)
        })

        }
}