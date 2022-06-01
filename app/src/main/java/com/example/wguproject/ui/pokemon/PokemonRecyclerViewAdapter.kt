package com.example.wguproject.ui.pokemon
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wguproject.R
import com.example.wguproject.data.response.pokemon.Ability
import com.example.wguproject.data.response.pokemonlist.PokemonList
import com.example.wguproject.ui.pokemon.PokemonMainActivity

class PokemonRecyclerViewAdapter() : RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {


    private val mList = mutableListOf<Ability>()
    private lateinit var context: Context

    //Refreshes the recyclerview once the information is loaded from the livedata
    fun submitList(newData: List<Ability>) {
        mList.clear()
        mList.addAll(newData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.ability.text = ItemsViewModel.ability.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val ability: TextView = itemView.findViewById(R.id.abilityName)
    }
}
