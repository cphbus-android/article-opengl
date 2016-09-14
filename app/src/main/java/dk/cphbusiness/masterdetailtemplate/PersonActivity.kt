package dk.cphbusiness.masterdetailtemplate

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dk.cphbusiness.masterdetailtemplate.model.Person
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        val person = intent.getSerializableExtra("person") as Person
        personId.text = person.id.toString()
        personFirstName.setText(person.firstName)
        personLastName.setText(person.lastName)
        personAge.text = person.age.toString()
        }

    fun okButtonClicked(view: View) {
        finish()
        }

    override fun finish() {
        // toast("Knap trykket")
        val person = Person(
                personId.text.toString().toInt(),
                personFirstName.text.toString(),
                personLastName.text.toString(),
                personAge.text.toString().toInt()
            )
        val result = Intent()
        result.putExtra("person", person)
        setResult(Activity.RESULT_OK, result)
        super.finish()
        }
}
