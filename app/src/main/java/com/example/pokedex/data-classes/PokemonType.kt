// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class PokemonType : RealmObject {

    var type: String? = null
    lateinit var coverage: RealmList<Coverage>
    lateinit var top_dual: RealmList<Long?>
    lateinit var top_pure: RealmList<Long?>
    lateinit var moves: RealmList<Long?>

}
