// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class PokemonNew : RealmObject {

    @PrimaryKey
    var id: Long = 0
    var num: Long = 0
    var types: RealmList<RealmString> = realmListOf()
    var heightm: Float = 0.0f
    var weightkg: Float = 0.0f
    var evos: RealmList<RealmString> = realmListOf()
    var name: String? = null
    var prevo: String? = null
    var prevoDetails: String? = null
    var evoLevel: Long = 0
    var formVersion: Long = 0
    var hasMegaEvo: Boolean = false
    var hasFourEvos: Boolean = false
    var stats: Stats? = null
    var description: String? = null
    lateinit var abilities: RealmList<PokemonAbility>
    var altImageUrl: String? = null
    var genus: String? = null
    lateinit var weaknesses: RealmList<RealmString>
    lateinit var weaknessMultipliers: RealmList<RealmInteger>
    lateinit var resistances: RealmList<RealmString>
    lateinit var resistanceMultipliers: RealmList<RealmInteger>
    var gen: Long = 0
    var filterType: Long = 0
    lateinit var eggGroup: RealmList<RealmInteger>
    var catchRate: Long = 0
    var genderRatio: Float = 0.0f
    var levelingRate: Long = 0
    var hasShinyVariant: Boolean = false
    lateinit var filterTypeList: RealmList<RealmInteger>
    lateinit var evs: RealmList<RealmInteger>
    var isCaught: Boolean = false
    var dominantColor: Long = 0
    var mutedColor: Long = 0
    var darkMutedColor: Long = 0
    var lightMutedColor: Long = 0
    var statTotal: Long = 0
    var favorite: Boolean = false

}
