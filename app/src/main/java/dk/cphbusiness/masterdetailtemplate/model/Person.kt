package dk.cphbusiness.masterdetailtemplate.model

import java.io.Serializable

data class Person(
        val id: Int,
        var firstName: String,
        var lastName: String,
        var age: Int
        ) : Serializable