package com.example.wguproject.ui.pokemonlist
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wguproject.R
import com.example.wguproject.data.response.pokemonlist.PokemonList
import com.example.wguproject.ui.pokemon.PokemonMainActivity

class PokemonListRecyclerViewAdapter() : RecyclerView.Adapter<PokemonListRecyclerViewAdapter.ViewHolder>() {


        private val mList = mutableListOf<PokemonList>()
        private lateinit var context: Context

        //Refreshes the recyclerview once the information is loaded from the livedata
        fun submitList(newData: List<PokemonList>) {
            mList.clear()
            mList.addAll(newData)
            notifyDataSetChanged()
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemon_list_item, parent, false)

            //Onclick listener for the recyclerview items
            return ViewHolder(view).listen { pos, type ->
                val item = mList.get(pos)
                val intent = Intent(context, PokemonMainActivity::class.java)
                intent.putExtra("pokemonName", item.name)
                intent.putExtra("pokemonUrl", item.url)
                startActivity(context, intent, null)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val ItemsViewModel = mList[position]
            holder.pokemonName.text = ItemsViewModel.name
        }

        override fun getItemCount(): Int {
            return mList.size
        }

        class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
            val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        }

        fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
            context = itemView.context
            itemView.setOnClickListener {
                event.invoke(getAdapterPosition(), getItemViewType())
            }
            return this
        }
    }
