// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class GameDex : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var idImg: Long = 0
    var name: String? = null
    var nickname: String? = null
    lateinit var games: RealmList<String?>
    lateinit var coverIds: RealmList<Long?>
    lateinit var regionalDexList: RealmList<RegionDex>

}
