package dk.cphbusiness.masterdetailtemplate

import com.google.gson.Gson

import dk.cphbusiness.masterdetailtemplate.model.Person

/**
 * Created by AKA on 21/09/16.
 */
class Testing {

    internal fun testing() {
        Gson().fromJson("...", Array<Person>::class.java)
    }
}
