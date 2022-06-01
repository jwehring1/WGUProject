package com.example.wguproject.ui.pokemonlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wguproject.R

class PokemonListMainActivity : AppCompatActivity() {
    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_list_main)

        //Initializing the pokemonviewfactory and viewmodel
        val factory = PokemonListViewFactory()
        viewModel = ViewModelProviders.of(this,factory).get(PokemonListViewModel::class.java)

        //Creating the recyclerview
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = PokemonListRecyclerViewAdapter()
        recyclerview.adapter = adapter

        //Waits for the viewmodel coroutine to grab all the pokemon from the api and then fills in the recyclerview
        viewModel.pokemonList.observe(this, Observer { it ->
            adapter.submitList(it)
        })
    }
}