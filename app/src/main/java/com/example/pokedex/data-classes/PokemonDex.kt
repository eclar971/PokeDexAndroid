// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject

open class PokemonDex : RealmObject {

    var id: Long = 0
    var regionalNum: Long = 0
    var name: String? = null
    var dominantColor: Long = 0
    var isCaught: Boolean = false
    var isSeen: Boolean = false
    var shiny: Boolean = false

}
