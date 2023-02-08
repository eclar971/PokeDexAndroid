// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class Encounter : RealmObject {

    var pokeId: Long = 0
    var gameId: Long = 0
    lateinit var gameLocation: RealmList<Long?>
    var encounterType: Long = 0
    var encounterDetails: Long = 0
    var encounterDetailExtras: String? = null
    var level: String? = null
    lateinit var rate: RealmList<String?>

}
