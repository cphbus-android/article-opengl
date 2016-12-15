package dk.kalhauge

import kotlin.reflect.KProperty

class OldPerson(var name: String, var age: Int)

class Adapter {
    operator fun getValue(thisRef: Person, property: KProperty<*>): String? {
      //  if (thisRef !is Person) return null
        when (property.name) {
            "firstName" -> return thisRef.oldie.name.split(" ")[0]
            "lastName" -> return thisRef.oldie.name.split(" ")[1]
            else -> return null
            }
        }
    }


class Person(val oldie: OldPerson) {
    val firstName: String? by Adapter()
    val lastName: String? by Adapter()
    val age: Long
        get() = oldie.age.toLong()
    }

class OldPerson2(var name: String, var age: Int)

operator fun OldPerson2.getValue(thisRef: Any?, property: KProperty<*>): String? {
    when (property.name) {
        "firstName" -> return this.name.split(" ")[0]
        "lastName" -> return this.name.split(" ")[1]
        else -> return null
        }
    }

class OtherPerson(data: OldPerson2) {
    val firstName: String? by data
    val lastName: String? by data
    }

fun main(args: Array<String>) {
    val data = OldPerson("Kurt Hansen", 45)
    val person = Person(data)
    println("fornavn: ${person.firstName} og efternavn: ${person.lastName}")
    val data2 = OldPerson2("Sonja Jensen", 45)
    val person2 = OtherPerson(data2)
    println("fornavn: ${person2.firstName} og efternavn: ${person2.lastName}")
    }