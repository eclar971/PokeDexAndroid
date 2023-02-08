// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class TeamPokemon : RealmObject {

    var pokeId: Long = 0
    lateinit var moveIds: RealmList<RealmInteger>
    var nature: String? = null
    var item: String? = null
    var nickname: String? = null
    var ability: String? = null
    var level: Long = 0
    var teamType: Long = 0
    var teraType: String? = null

}
