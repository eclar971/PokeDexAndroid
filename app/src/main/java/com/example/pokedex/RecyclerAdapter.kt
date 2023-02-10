package com.example.pokedex

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.*
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*


class RecyclerAdapter (val activity: MainActivity, val pokemonList : MutableList<pokemon>,
                       val itemClickCallback: ((Int,pokemon) -> Unit)?)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    var color: Int = 0
    var tempPokeList = pokemonList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,
            parent,false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView = holder.pokeImage
        val currentItem = tempPokeList[position]
        val currentMon = activity.dex.find {
            it.name == currentItem.pokeName
        }
        color = currentMon!!.dominantColor.toInt()
        Glide
            .with(imageView.context)
            .load(currentItem.pokeImage)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(imageView)
        val myIcon: Drawable = holder.background.drawable
        val tempColor = Color.rgb(Color.red(color),Color.green(color),Color.blue(color))
        val colorInt = ColorUtils.setAlphaComponent(tempColor,20)
        val filter: ColorFilter = LightingColorFilter(Color.BLACK, colorInt)
        myIcon.colorFilter = filter
        var tempName = currentItem.pokeName
        tempName = tempName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        holder.pokeName.text = "${tempName}\n #${currentMon.num}"
        holder.itemView.setOnClickListener {
            itemClickCallback!!.invoke(colorInt,currentItem)
            it.findNavController().navigate(R.id.action_nav_home_to_nav_gallery)
        }
    }
    override fun getItemCount(): Int {
        return pokemonList.size
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokeImage : ImageView = itemView.findViewById(R.id.poke_image)
        val pokeName : TextView = itemView.findViewById(R.id.poke_name)
        val background : ImageView = itemView.findViewById(R.id.rounded)
    }

    fun setFilter(funPokeList: Collection<pokemon>) {
        tempPokeList.clear()
        tempPokeList.addAll(funPokeList)
        notifyDataSetChanged()
    }
}
