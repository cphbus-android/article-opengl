package dk.cphbusiness.masterdetailtemplate.model

import java.io.Serializable
import java.util.*

data class Person(
        val map: MutableMap<String, Any?>
        ) : Serializable {
    var _id: Int by map
    var firstName: String by map
    var lastName: String by map
    var age: Int by map

    constructor(
            id: Int,
            firstName: String,
            lastName: String,
            age: Int
            ) : this(HashMap()) {
        this._id = id
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
        }
    }

object PersonTable {
    val NAME = "PEOPLE"
    val ID = "_id"
    val FIRST_NAME = "firstName"
    val LAST_NAME = "lastName"
    val AGE = "age"
    }


class Pet(var name: String)


