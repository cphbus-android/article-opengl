package dk.cphbusiness.masterdetailtemplate

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import dk.cphbusiness.masterdetailtemplate.model.Person
import java.net.URL

class PersonRequest() {
    fun run() {
        val peopleJson = URL("http://10.50.162.14:4711/Nice/person").readText()
        Log.d(this.javaClass.simpleName, peopleJson)
        val data = Gson().fromJson(peopleJson, Array<Person>::class.java)
        PeopleActivity.people = data.toMutableList()
        }
    }