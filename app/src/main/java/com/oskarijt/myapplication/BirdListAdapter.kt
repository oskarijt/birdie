package com.oskarijt.myapplication

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BirdListAdapter(private val birdList: List<BirdModel>) : RecyclerView.Adapter<BirdListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(birdList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return birdList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(bird: BirdModel) {
            val sdf = SimpleDateFormat("dd.MM.yyyy', 'HH:mm'")

            val txtSpecies = itemView.findViewById(R.id.textViewSpecies) as TextView
            val txtRarity  = itemView.findViewById(R.id.textViewRarity) as TextView
            val txtNotes = itemView.findViewById(R.id.textViewNotes) as TextView
            val txtDate = itemView.findViewById(R.id.textViewDate) as TextView

            Log.d("TAG", bird.species)

            txtSpecies.text = bird.species
            txtRarity.text = bird.rarity
            txtNotes.text = bird.notes
            txtDate.text = sdf.format(bird.spotted_at).toString()
        }
    }
}