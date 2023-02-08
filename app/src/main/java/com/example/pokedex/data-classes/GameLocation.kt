// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class GameLocation : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var red: String? = null
    var blue: String? = null
    var yellow: String? = null
    var gold: String? = null
    var silver: String? = null
    var crystal: String? = null
    var ruby: String? = null
    var sapphire: String? = null
    var emerald: String? = null
    var firered: String? = null
    var leafgreen: String? = null
    var diamond: String? = null
    var pearl: String? = null
    var platinum: String? = null
    var heartgold: String? = null
    var soulsilver: String? = null
    var black: String? = null
    var white: String? = null
    var black2: String? = null
    var white2: String? = null
    var x: String? = null
    var y: String? = null
    var omegaruby: String? = null
    var alphasapphire: String? = null
    var sun: String? = null
    var moon: String? = null
    var ultrasun: String? = null
    var ultramoon: String? = null
    var lgp: String? = null
    var lge: String? = null
    var sw: String? = null
    var sh: String? = null
    var bd: String? = null
    var sp: String? = null
    var la: String? = null
    var sc: String? = null
    var vi: String? = null

}
