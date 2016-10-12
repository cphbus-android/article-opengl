package dk.cphbusiness.masterdetailtemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dk.cphbusiness.masterdetailtemplate.model.Person
import kotlinx.android.synthetic.main.person_item.view.*


class PersonArrayAdapter(context: Context, people: List<Person>) :
        ArrayAdapter<Person>(context, 0, people) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val person = getItem(position)
        var template : View =
                convertView ?:
                        LayoutInflater.from(context).inflate(R.layout.person_item, parent, false)
        template.ID.text = "#${person._id}"
        template.firstName.text = person.firstName
        template.lastName.text = person.lastName
        return template

    }
}
