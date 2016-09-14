package dk.cphbusiness.masterdetailtemplate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }

    fun buttonPressed(view: View) {
        Toast.makeText(this, "Pressed", Toast.LENGTH_LONG).show()
        }

    }
