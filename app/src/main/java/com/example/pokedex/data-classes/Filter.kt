// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Filter : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var specialId: Long = 0
    lateinit var filterObjects: RealmList<FilterObject>
    var isActive: Boolean = false
    var sort: Long = 0

}
