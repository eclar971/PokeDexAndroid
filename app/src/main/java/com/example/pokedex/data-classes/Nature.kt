// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject

open class Nature : RealmObject {

    var name: String? = null
    var increasedStat: Long = 0
    var decreasedStat: Long = 0
    var favoriteFlavor: Long = 0
    var dislikedFlavor: Long = 0

}
