// Please note : @LinkingObjects and default values are not represented in the schema and thus will not be part of the generated models
package com.example.pokedex.`data-classes`

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

open class Coverage : RealmObject {

    lateinit var gen: RealmList<Long?>
    lateinit var off_double: RealmList<String?>
    lateinit var off_half: RealmList<String?>
    lateinit var off_no_effect: RealmList<String?>
    lateinit var def_half: RealmList<String?>
    lateinit var def_double: RealmList<String?>
    lateinit var def_no_effect: RealmList<String?>

}
