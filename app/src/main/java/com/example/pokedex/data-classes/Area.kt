// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class Area : RealmObject {
    var region: String? = null
    var name: String? = null
    var description: String? = null
    var connectingLocations: RealmList<String?> = realmListOf()
    lateinit var subAreas: RealmList<SubArea>

}
