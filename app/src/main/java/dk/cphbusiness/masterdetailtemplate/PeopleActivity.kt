package dk.cphbusiness.masterdetailtemplate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import dk.cphbusiness.masterdetailtemplate.model.Person
import kotlinx.android.synthetic.main.activity_people.*

class PeopleActivity : AppCompatActivity() {

    companion object {
        var people = mutableListOf(
                Person(1, "Kurt", "Hansen", 34),
                Person(2, "Sonja", "Hansen", 19),
                Person(3, "Mikkel", "Petersen", 23),
                Person(4, "Viggo", "Kampmann", 56)
            )

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        peopleList.adapter = PersonArrayAdapter(
                this,
                people)
        peopleList.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, pos, id ->
            val clicked: Person = peopleList.getItemAtPosition(pos) as Person
            val intent = Intent(this, PersonActivity::class.java)

            intent.putExtra("person", clicked)
            startActivityForResult(intent, 4712)
        }
    }

    private fun updatePerson(person: Person) {
        for (p in people) {
            if (p.id == person.id) {
                p.firstName = person.firstName
                p.lastName = person.lastName
                p.age = person.age
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        toast("REQUEST CODE: $requestCode")
        if (data?.getSerializableExtra("person") != null) {
            val person: Person = data?.getSerializableExtra("person") as Person
            if (person != null) updatePerson(person)
            peopleList.adapter = PersonArrayAdapter(this, people)
            }
        }

    }
