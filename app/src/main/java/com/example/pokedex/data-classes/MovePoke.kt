// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject

open class MovePoke : RealmObject {

    var moveId: Long = 0
    var pokeId: Long = 0

}
