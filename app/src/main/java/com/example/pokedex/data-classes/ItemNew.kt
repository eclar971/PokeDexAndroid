// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class ItemNew : RealmObject {

    var name: String? = null
    var category: String? = null
    var effect: String? = null
    var shortEffect: String? = null
    var cost: Long = 0
    lateinit var attributes: RealmList<RealmString>
    var flavorText: String? = null
    var itemLocation: ItemLocation? = null
    var gen: Long = 0
    var allGens: String? = null

}
