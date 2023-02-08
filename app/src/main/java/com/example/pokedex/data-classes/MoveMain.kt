// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class MoveMain : RealmObject {

    var type: BaseUrlName? = null
    var damage_class: BaseUrlName? = null
    lateinit var stat_changes: RealmList<StatChange>
    lateinit var effect_entries: RealmList<EffectEntry>
    lateinit var flavor_text_entries: RealmList<FlavorTextEntry>
    var generation: BaseUrlName? = null
    var name: String? = null
    var id: Long = 0
    var power: Long = 0
    var pp: Long = 0
    var priority: Long = 0
    var effect_chance: Long = 0
    var accuracy: Long = 0

}
