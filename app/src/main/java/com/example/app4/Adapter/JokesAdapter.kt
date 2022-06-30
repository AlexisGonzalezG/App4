package com.example.app4.Adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app4.Model.Jokes
import com.example.app4.databinding.ActivityMainBinding
import com.example.app4.databinding.JokeItemBinding

class JokesAdapter(
    private val jokesSet: MutableList<Jokes> = mutableListOf(),
    private val onJokeClicked: (String?)-> Unit

): RecyclerView.Adapter<JokersViewHolder>() {


    fun anotherJoke(){
        jokesSet.clear()
    }


    fun updateNewJokes(newJokes: Jokes){
        Log.d(ContentValues.TAG, "ALEXIS newJokes:" + newJokes)

        jokesSet.clear()
        jokesSet.add(newJokes)
        notifyDataSetChanged()
        //notifyItemInserted(0)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokersViewHolder =
        JokersViewHolder(
            JokeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )

    override fun onBindViewHolder(holder: JokersViewHolder, position: Int) =
        holder.bind(jokesSet[position],onJokeClicked)

    override fun getItemCount(): Int = jokesSet.size


}

class JokersViewHolder(
    private val binding : JokeItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(joke:  Jokes,onJokeClicked: (String?) -> Unit){

        Log.d(ContentValues.TAG, "ALEXIS JokersViewHolder:" + joke)

        binding.cardId.text = joke.id.toString()
        binding.cardJoke.text = joke.joke

        itemView.setOnClickListener {
            onJokeClicked(joke.id.toString())
        }
    }

}