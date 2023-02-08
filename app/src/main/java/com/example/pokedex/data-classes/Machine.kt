// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Machine : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var gen: Long = 0
    var moveId: Long = 0
    var num: Long = 0

}
