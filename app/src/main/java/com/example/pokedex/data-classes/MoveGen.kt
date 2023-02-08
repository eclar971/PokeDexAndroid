// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class MoveGen : RealmObject {

    var gen: Long = 0
    lateinit var egg: RealmList<RealmInteger>
    lateinit var levelUpMove: RealmList<RealmInteger>
    lateinit var levelUpNum: RealmList<RealmInteger>
    lateinit var machine: RealmList<RealmInteger>
    lateinit var tutor: RealmList<RealmInteger>
    lateinit var priorMoves: RealmList<MovePoke>

}
