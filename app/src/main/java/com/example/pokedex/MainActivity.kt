// Ernest Clark
// Capstone Project
// 2/7/2023s
package com.example.pokedex

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.pokedex.`data-classes`.*
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.ui.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var pokemonList = mutableListOf<pokemon>()
    var pokeArray = listOf<JsonPokemon>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = "https://pokeapi.co/api/v2/pokemon?limit=1008&offset=0"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val listMyData =
                    Types.newParameterizedType(MutableList::class.java, JsonPokemon::class.java)
                val moshiAdapter: JsonAdapter<List<JsonPokemon>> = Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build().adapter(listMyData)
                val temp = response.getJSONArray("results")
                File("/data/user/0/com.example.pokedex/files/default.realm").writeBytes(applicationContext!!.assets.open("default.realm").readBytes())
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
                pokeArray = moshiAdapter.fromJson(temp.toString())!!
                for (i in IntRange(0, pokeArray.size)) {
                        Glide.with(applicationContext!!)
                            .load(
                                if (i + 1 <= 1008) {
                                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                            (i + 1).toString().padStart(3, '0') +
                                            ".png"
                                }else{
                                    "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" +
                                            (i).toString().padStart(3, '0') +
                                            ".png"
                                }
                            )
                            .into(object : CustomTarget<Drawable>() {
                                override fun onResourceReady(
                                    resource: Drawable,
                                    transition: Transition<in Drawable>?
                                ) {
                                    if (pokemonList.size <= 1007) {
                                        pokemonList.add(pokemon(resource, dex.find { it.num.toInt() == i + 1}!!.name!!))
                                        /*if (pokemonList.size >= 1008){
                                        gif.setFreezesAnimation(true)
                                        gif.visibility = View.GONE
                                    }*/
                                    }else{
                                        binding = ActivityMainBinding.inflate(layoutInflater)
                                        setContentView(binding.root)
                                        setSupportActionBar(binding.appBarMain.toolbar)

                                        binding.appBarMain.fab.setOnClickListener { view ->
                                            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show()
                                        }
                                        val drawerLayout: DrawerLayout = binding.drawerLayout
                                        val navView: NavigationView = binding.navView
                                        val navController = findNavController(R.id.nav_host_fragment_content_main)
                                        // Passing each menu ID as a set of Ids because each
                                        // menu should be considered as top level destinations.
                                        appBarConfiguration = AppBarConfiguration(setOf(
                                            R.id.nav_home), drawerLayout)
                                        setupActionBarWithNavController(navController, appBarConfiguration)
                                        navView.setupWithNavController(navController)
                                    }
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {
                                    TODO("Not yet implemented")
                                }

                            })

                }
            },
            { _ ->
                // TODO: Handle error
            }
        )
        Volley.newRequestQueue(applicationContext).add(jsonObjectRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
