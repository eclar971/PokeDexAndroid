package com.example.pokedex.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokedex.MainActivity
import com.example.pokedex.`data-classes`.*
import com.example.pokedex.databinding.FragmentCloseviewBinding
import com.example.pokedex.pokemon
import java.util.*


class CloseViewFragment : Fragment() {
    private val typeToColor = mapOf(
        "Normal" to "#A8A77A",
        "Fire" to "#EE8130",
        "Water" to "#6390F0",
        "Electric" to "#F7D02C",
        "Grass" to "#7AC74C",
        "Ice" to "#96D9D6",
        "Fighting" to "#C22E28",
        "Poison" to "#A33EA1",
        "Ground" to "#E2BF65",
        "Flying" to "#A98FF3",
        "Psychic" to "#F95587",
        "Bug" to "#A6B91A",
        "Rock" to "#B6A136",
        "Ghost" to "#735797",
        "Dragon" to "#6F35FC",
        "Dark" to "#705746",
        "Steel" to "#B7B7CE",
        "Fairy" to "#D685AD"
    )
    private val sharedViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentCloseviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("SdCardPath", "SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCloseviewBinding.inflate(inflater, container, false)
        val background = binding.name
        val round = binding.round
        val image = binding.imageView2
        var currentPoke = sharedViewModel.currentPoke.value!!
        val colorPoke = sharedViewModel.pokePallet.value!!
        val type1 = binding.type1
        val type2 = binding.type2
        val size = binding.size
        val genus = binding.genus
        val description = binding.description
        val evo1 = binding.firstEvo
        val evo2 = binding.secondEvo
        val evo3 = binding.thirdEvo
        val megaText = binding.megaText
        val arrow1 = binding.firstArrow
        val arrow2 = binding.secondArrow
        val baseEvo3 = binding.baseEvo3
        val megaEvo3 = binding.megaEvo3
        val megaArrow = binding.megaArrow
        val mainActivity = (activity as MainActivity)
        val dex = mainActivity.dex
        var dexMon = dex.find {
            it.name == currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                Locale.ROOT) else it.toString() }
        }
        round.setBackgroundColor(dexMon!!.dominantColor.toInt())
        background.text = " ${currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
            Locale.ROOT) else it.toString() }} #${dexMon.num.toString().padStart(3, '0')} "
        type1.text = dexMon.types[0].value.toString()
        type1.setBackgroundColor(Color.parseColor(typeToColor["${type1.text}"]))
        if (dexMon.types.size > 1){
            type2.text = dexMon.types[1].value.toString()
            type2.setBackgroundColor(Color.parseColor(typeToColor["${type2.text}"]))
        }
        val prevo = dexMon.prevo
        val firstEvo = prevo == null
        val hasMega = dexMon.hasMegaEvo
        val evo = try{dexMon.evos[0].value}catch (e:java.lang.Exception){null}
        val doesEvo = if (!hasMega){
            evo != null
        }else{
            false
        }
        val isNotLastEvo = (!firstEvo)&&(doesEvo)
        if (firstEvo){
            val evo1Obj = dexMon
            loadGlide(currentPoke,evo1)
            if(evo1Obj.evos.size != 0 && !evo1Obj.hasMegaEvo){
                val evo2obj = dex.find{
                    it.name!!.lowercase() == evo1Obj.evos[0].value!!.lowercase()
                }
                loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo2obj!!.name!!.lowercase() }!!,evo2)
                if(evo2obj!!.evos.size != 0 && !evo2obj.hasMegaEvo){
                    val evo3obj = dex.find{
                        it.name!!.lowercase() == evo2obj!!.evos[0].value!!.lowercase()
                    }
                    loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo3obj!!.name!!.lowercase() }!!,evo3)
                    if (evo3obj!!.hasMegaEvo){
                        loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo3obj.name!!.lowercase() }!!,baseEvo3)
                        loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                (evo3obj.num).toString().padStart(3, '0') +
                                "_f2.png", "${evo3obj.name}-Mega"),megaEvo3)
                    }else{
                        hide(listOf(baseEvo3,megaEvo3,megaArrow,megaText))
                    }
                }else{
                    hide(listOf(evo3,arrow2))
                    if (evo2obj.hasMegaEvo){
                        loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo2obj.name!!.lowercase() }!!,baseEvo3)
                        loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                (evo2obj.num).toString().padStart(3, '0') +
                                "_f2.png", "${evo2obj.name}-Mega"),megaEvo3)
                    }else{
                        hide(listOf(baseEvo3,megaEvo3,megaArrow,megaText))
                    }
                }
            }else{
                hide(listOf(evo3,arrow2,evo1,arrow1))
                if (evo1Obj.hasMegaEvo){
                    loadGlide(currentPoke,evo2)
                    loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo1Obj.name!!.lowercase() }!!,baseEvo3)
                    loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                            (evo1Obj.num).toString().padStart(3, '0') +
                            "_f2.png", "${evo1Obj.name}-Mega"),megaEvo3)
                }else{
                    loadGlide(currentPoke,evo2)
                    hide(listOf(baseEvo3,megaEvo3,megaArrow,megaText))
                }
            }
        }
        else if (!firstEvo && isNotLastEvo){
            val evo2obj = dexMon
            loadGlide(currentPoke,evo2)
            val evo1Obj = dex.find{
                it.name!!.lowercase() == evo2obj.prevo!!.lowercase()
            }
            loadGlide(
                mainActivity.pokemonList.find { it.pokeName.lowercase() == evo1Obj!!.name!!.lowercase() }!!,
                evo1
            )
            if(doesEvo && !hasMega) {
                val evo3obj = dex.find {
                    it.name!!.lowercase() == evo2obj!!.evos[0].value!!.lowercase()
                }
                loadGlide(
                    mainActivity.pokemonList.find { it.pokeName.lowercase() == evo3obj!!.name!!.lowercase() }!!,
                    evo3
                )
                if (evo3obj!!.hasMegaEvo) {
                    loadGlide(
                        mainActivity.pokemonList.find { it.pokeName.lowercase() == evo3obj.name!!.lowercase() }!!,
                        baseEvo3
                    )
                    loadGlide(
                        pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                (evo3obj.num).toString().padStart(3, '0') +
                                "_f2.png", "${evo3obj.name}-Mega"), megaEvo3
                    )
                }else{
                    hide(listOf(megaText,megaArrow,megaEvo3,baseEvo3))
                }
            }else{
                if (evo2obj.hasMegaEvo){
                    hide(listOf(arrow2,evo3))
                    loadGlide(
                        mainActivity.pokemonList.find { it.pokeName.lowercase() == evo2obj.name!!.lowercase() }!!,
                        baseEvo3
                    )
                    loadGlide(
                        pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                (evo2obj.num).toString().padStart(3, '0') +
                                "_f2.png", "${evo2obj.name}-Mega"), megaEvo3
                    )
                }else{
                    hide(listOf(arrow2,evo3,megaArrow,megaEvo3,megaText,baseEvo3))
                }
            }
        }
        else if (!firstEvo){
            var evo2Obj = dex.find{
                it.name!!.lowercase() == dexMon!!.prevo!!.lowercase()
            }
            if (evo2Obj!!.prevo != null){
                val evo1Obj = dex.find{
                    it.name!!.lowercase() == evo2Obj!!.prevo!!.lowercase()
                }
                try{
                    loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo1Obj!!.name!!.lowercase() }!!,evo1)
                }catch (e:java.lang.Exception){
                    loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                            (evo1Obj!!.num).toString().padStart(3, '0') +
                            "_f2.png", "${evo1Obj.name}-Mega"),evo1)
                }
                try {
                    loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo2Obj!!.name!!.lowercase() }!!,evo2)
                }catch (e:java.lang.Exception){
                    loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                            (evo2Obj.num).toString().padStart(3, '0') +
                            "_f2.png", "${evo2Obj.name}-Mega"),evo2)
                }

                val evo3Obj = dexMon
                loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo3Obj.name!!.lowercase() }!!,evo3)
                if (evo3Obj.hasMegaEvo){
                    loadGlide(currentPoke,baseEvo3)
                    loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                            (evo3Obj.num).toString().padStart(3, '0') +
                            "_f2.png", "${evo3Obj.name}-Mega"),megaEvo3)
                }else{
                    hide(listOf(megaText,megaArrow,megaEvo3,baseEvo3))
                }
            }else{
                hide(listOf(arrow2,evo3))
                evo2Obj = dexMon
                loadGlide(currentPoke,evo2)
                val evo1Obj = dex.find{
                    it.name!!.lowercase() == evo2Obj.prevo!!.lowercase()
                }
                try{
                    loadGlide(mainActivity.pokemonList.find { it.pokeName.lowercase() == evo1Obj!!.name!!.lowercase() }!!,evo1)
                }catch (e:java.lang.Exception){
                    loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                            (evo1Obj!!.num).toString().padStart(3, '0') +
                            "_f2.png", "${evo1Obj.name}-Mega"),evo1)
                }
                if (evo2Obj.hasMegaEvo){
                    loadGlide(currentPoke,baseEvo3)
                    loadGlide(pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                            (evo2Obj.num).toString().padStart(3, '0') +
                            "_f2.png", "${evo2Obj.name}-Mega"),megaEvo3)
                }else{
                    hide(listOf(megaText,megaArrow,megaEvo3,baseEvo3))
                }
            }
        }
        genus.text = "  ${dexMon.genus} Pokemon"
        size.text = "Weight:${dexMon.weightkg} kg\nHeight:${dexMon.heightm} m"
        description.text = "Description:\n\n      ${dexMon.description}"
        loadGlide(currentPoke,image)
        evo1.setOnClickListener{
            currentPoke = mainActivity.pokemonList.find { it.pokeName == evo1.contentDescription}!!
            dexMon = dex.find {
                it.name == currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT) else it.toString() }
            }
            genus.text = "  ${dexMon!!.genus} Pokemon"
            size.text = "Weight:${dexMon!!.weightkg} kg\nHeight:${dexMon!!.heightm} m"
            description.text = "Description:\n\n      ${dexMon!!.description}"
            round.setBackgroundColor(dexMon!!.dominantColor.toInt())
            background.text = " ${currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                Locale.ROOT) else it.toString() }} #${dexMon!!.num.toString().padStart(3, '0')} "
            type1.text = dexMon!!.types[0].value.toString()
            type1.setBackgroundColor(Color.parseColor(typeToColor["${type1.text}"]))
            if (dexMon!!.types.size > 1){
                type2.text = dexMon!!.types[1].value.toString()
                type2.setBackgroundColor(Color.parseColor(typeToColor["${type2.text}"]))
            }
            loadGlide(currentPoke,image)
        }
        evo2.setOnClickListener{
            currentPoke = mainActivity.pokemonList.find { it.pokeName == evo2.contentDescription}!!
            dexMon = dex.find {
                it.name == currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT) else it.toString() }
            }
            genus.text = "  ${dexMon!!.genus} Pokemon"
            size.text = "Weight:${dexMon!!.weightkg} kg\nHeight:${dexMon!!.heightm} m"
            description.text = "Description:\n\n      ${dexMon!!.description}"
            round.setBackgroundColor(dexMon!!.dominantColor.toInt())
            background.text = " ${currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                Locale.ROOT) else it.toString() }} #${dexMon!!.num.toString().padStart(3, '0')} "
            type1.text = dexMon!!.types[0].value.toString()
            type1.setBackgroundColor(Color.parseColor(typeToColor["${type1.text}"]))
            if (dexMon!!.types.size > 1){
                type2.text = dexMon!!.types[1].value.toString()
                type2.setBackgroundColor(Color.parseColor(typeToColor["${type2.text}"]))
            }
            loadGlide(currentPoke,image)
        }
        evo3.setOnClickListener{
            currentPoke = mainActivity.pokemonList.find { it.pokeName == evo3.contentDescription}!!
            dexMon = dex.find {
                it.name == currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT) else it.toString() }
            }
            genus.text = "  ${dexMon!!.genus} Pokemon"
            size.text = "Weight:${dexMon!!.weightkg} kg\nHeight:${dexMon!!.heightm} m"
            description.text = "Description:\n\n      ${dexMon!!.description}"
            round.setBackgroundColor(dexMon!!.dominantColor.toInt())
            background.text = " ${currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                Locale.ROOT) else it.toString() }} #${dexMon!!.num.toString().padStart(3, '0')} "
            type1.text = dexMon!!.types[0].value.toString()
            type1.setBackgroundColor(Color.parseColor(typeToColor["${type1.text}"]))
            if (dexMon!!.types.size > 1){
                type2.text = dexMon!!.types[1].value.toString()
                type2.setBackgroundColor(Color.parseColor(typeToColor["${type2.text}"]))
            }
            loadGlide(currentPoke,image)
        }
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
fun loadGlide(path : pokemon, element : ImageView){
    Glide
        .with(element.context!!)
        .load(path.pokeImage)
        .thumbnail(0.05f)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(element)
    element.contentDescription = path.pokeName
}
fun hide(elements: List<View>){
    elements.forEach {
        it.visibility = View.GONE
    }
}