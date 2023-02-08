// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class PokemonMove : RealmObject {

    var name: String? = null
    lateinit var pokemonMoves: RealmList<Move>

}
