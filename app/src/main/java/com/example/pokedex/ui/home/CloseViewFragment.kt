package com.example.pokedex.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pokedex.MainActivity
import com.example.pokedex.R
import com.example.pokedex.`data-classes`.*
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.databinding.FragmentCloseviewBinding
import com.example.pokedex.pokemon
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import java.io.File
import java.nio.file.Path
import java.util.*
import javax.xml.validation.Schema

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
    private lateinit var realm : Realm
    private val sharedViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentCloseviewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        val currentPoke = sharedViewModel.currentPoke.value!!
        val colorPoke = sharedViewModel.pokePallet.value!!
        val type1 = binding.type1
        val type2 = binding.type2
        val size = binding.size
        val genus = binding.genus
        val description = binding.description
        val evo1 = binding.firstEvo
        val evo2 = binding.secondEvo
        val evo3 = binding.thirdEvo
        val arrow1 = binding.firstArrow
        val arrow2 = binding.secondArrow
        round.setBackgroundColor(colorPoke)
        val configuration = RealmConfiguration.Builder(schema = setOf(
            AbilitiesDex::class,
            Ability::class,
            Area::class,
            BaseUrlName::class,
            Coverage::class,
            EffectEntry::class,
            Encounter::class,
            Evo::class,
            Filter::class,
            FilterObject::class,
            FlavorTextEntry::class,
            FullPokemonMove::class,
            FullPokemonMovesDex::class,
            GameDex::class,
            GameLocation::class,
            GenerationAbility::class,
            GymLeader::class,
            GymTeam::class,
            ItemLocation::class,
            ItemNew::class,
            Location::class,
            Machine::class,
            Move::class,
            MoveGen::class,
            MoveMain::class,
            MovePoke::class,
            MovesDex::class,
            Nature::class,
            PokeDex::class,
            PokemonAbility::class,
            PokemonDex::class,
            PokemonMove::class,
            PokemonMovesDex::class,
            PokemonNew::class,
            PokemonType::class,
            RealmInteger::class,
            RealmString::class,
            RegionDex::class,
            StatChange::class,
            Stats::class,
            SubArea::class,
            Team::class,
            TeamPokemon::class,
            Trainer::class,
        )).schemaVersion(45).build()
        val realm: Realm = Realm.open(configuration)
        val dex = realm.query<PokeDex>().first().find()!!.pokemon
        val dexMon = dex.find {
            it.name == currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                Locale.ROOT) else it.toString() }
        }

        background.text = " ${currentPoke.pokeName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
            Locale.ROOT) else it.toString() }} #${dexMon!!.num.toString().padStart(3, '0')} "
        type1.text = dexMon.types[0].value.toString()
        type1.setBackgroundColor(Color.parseColor(typeToColor.get("${type1.text}")))
        if (dexMon.types.size > 1){
            type2.text = dexMon.types[1].value.toString()
            type2.setBackgroundColor(Color.parseColor(typeToColor.get("${type2.text}")))
        }
        val mainActivity = (activity as MainActivity)
        val isNotFirstEvo = dexMon.prevo
        val doesEvo = try {
            dexMon.evos[0].value
        }catch (e:IndexOutOfBoundsException){
            null
        }
        val hasMega = dexMon.hasMegaEvo
        if (isNotFirstEvo == null && doesEvo == null) {
            evo1.setImageDrawable(currentPoke.pokeImage)
            evo2.visibility = View.GONE
            evo3.visibility = View.GONE
            arrow1.visibility = View.GONE
            arrow2.visibility = View.GONE
        }else if (isNotFirstEvo == null) {
            try {
                var evo2Name = dexMon.evos[0].value
                val evo2NameSplit = evo2Name!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }.split("-")
                evo2NameSplit.forEach {
                    it.replaceFirstChar { it1 ->
                        if (it1.isLowerCase()) it1.titlecase(
                            Locale.ROOT
                        ) else it1.toString()
                    }
                }
                evo2Name = evo2NameSplit.joinToString("-")
                val evo2Obj = dex.find {
                    it.name == evo2Name!!.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                }
                evo2Name = evo2Obj!!.name
                evo1.setImageDrawable(currentPoke.pokeImage)
                if (dexMon.hasMegaEvo) {
                    Glide.with(context!!)
                        .load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                        (dexMon.num).toString().padStart(3, '0') +
                                        "_f2.png"
                        )
                        .into(evo2)

                }
                else {
                    evo2.setImageDrawable(mainActivity.pokemonList.find {
                        it.pokeName == evo2Name
                    }!!.pokeImage)
                }
                evo3.setImageDrawable(mainActivity.pokemonList.find {
                    it.pokeName == evo2Obj.evos[0].value!!.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                }!!.pokeImage)
            }catch (e:Exception){
                evo3.visibility = View.GONE
                arrow2.visibility = View.GONE
            }
        }else if (isNotFirstEvo != null && doesEvo != null && !hasMega) {
            try{
                val evo3Name = dexMon.evos[0].value!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                evo1.setImageDrawable(mainActivity.pokemonList.find {
                    it.pokeName == dexMon.prevo!!.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                }!!.pokeImage)
                if (dexMon.hasMegaEvo) {
                    Glide.with(context!!)
                        .load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                (dexMon.num).toString().padStart(3, '0') +
                                "_f2.png"
                        )
                        .into(object : CustomTarget<Drawable>() {
                            override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable>?
                            ) {
                                evo2.setImageDrawable(resource)
                            }
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }
                        })

                }else{
                    evo2.setImageDrawable(currentPoke.pokeImage)
                }
                evo3.setImageDrawable(mainActivity.pokemonList.find {it.pokeName == evo3Name}
                !!.pokeImage)
            }
            catch (e:Exception){
                evo3.visibility = View.GONE
                arrow2.visibility = View.GONE
            }
        }else if (isNotFirstEvo != null && (doesEvo == null || hasMega) ) {
            try{
            val evo2Name = dexMon.prevo!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            val evo2Obj = dex.find {
                it.name == evo2Name
            }
            val evo1Name = evo2Obj!!.prevo!!.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            evo1.setImageDrawable(mainActivity.pokemonList.find { it.pokeName == evo1Name
            }!!.pokeImage)
            evo2.setImageDrawable(mainActivity.pokemonList.find { it.pokeName == evo2Name }!!.pokeImage)
            evo3.setImageDrawable(currentPoke.pokeImage)
            }catch (e:Exception){
                evo1.setImageDrawable(mainActivity.pokemonList.find { it.pokeName == dexMon.prevo!!.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                }!!.pokeImage)
                evo2.setImageDrawable(currentPoke.pokeImage)
                evo3.visibility = View.GONE
                arrow2.visibility = View.GONE
            }
        }
        genus.text = "  ${dexMon.genus} Pokemon"
        size.text = "Weight:${dexMon.weightkg} kg\nHeight:${dexMon.heightm} m"
        description.text = "Description:\n\n      ${dexMon.description}"
        image.setImageDrawable(currentPoke.pokeImage)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}