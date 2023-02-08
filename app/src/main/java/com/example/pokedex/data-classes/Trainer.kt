// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Trainer : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var lastInstalledVersionCode: Long = 0
    var team: String? = null
    var showPokemonImages: Boolean = false
    var swipedForMore: Boolean = false
    var swipedForMoves: Boolean = false
    var seenShowImagesTooltip: Boolean = false
    var showOnlyFavorites: Boolean = false
    var pro: Boolean = false

}
