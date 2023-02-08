// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject

open class Stats : RealmObject {

    var defense: Long = 0
    var speed: Long = 0
    var specialAttack: Long = 0
    var specialDefense: Long = 0
    var hp: Long = 0
    var attack: Long = 0
    var max: Long = 0
    var total: Long = 0

}
