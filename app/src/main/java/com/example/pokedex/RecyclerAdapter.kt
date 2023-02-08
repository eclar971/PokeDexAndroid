package com.example.pokedex

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.red
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.ui.home.HomeFragment
import com.example.pokedex.ui.home.HomeViewModel
import java.util.*


class RecyclerAdapter (val pokeList : MutableList<pokemon>,var jsonPokeList: List<JsonPokemon>,
                       val itemClickCallback: ((Int,pokemon) -> Unit)?)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    lateinit var color: Palette.Swatch
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,
            parent,false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.pokeImage
        val currentItem = pokeList[position]
        val bitmap = currentItem.pokeImage.toBitmap()
        color = Palette.from(bitmap).generate().dominantSwatch!!
        val myIcon: Drawable = holder.background.drawable
        val filter: ColorFilter = LightingColorFilter(Color.WHITE, Color.HSVToColor(20,color.hsl))
        myIcon.colorFilter = filter
        imageView.setImageDrawable(currentItem.pokeImage)
        var tempName = currentItem.pokeName
        tempName = tempName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        holder.pokeName.text = "${tempName}\n #${position+1}"
        holder.itemView.setOnClickListener {
            itemClickCallback!!.invoke(Color.HSVToColor(100,Palette.from(currentItem.pokeImage.toBitmap()).generate().dominantSwatch!!.hsl),pokeList[position])
            it.findNavController().navigate(R.id.action_nav_home_to_nav_gallery)
        }
    }
    override fun getItemCount(): Int {
        return pokeList.size
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokeImage : ImageView = itemView.findViewById(R.id.poke_image)
        val pokeName : TextView = itemView.findViewById(R.id.poke_name)
        val background : ImageView = itemView.findViewById(R.id.rounded)
    }
}
