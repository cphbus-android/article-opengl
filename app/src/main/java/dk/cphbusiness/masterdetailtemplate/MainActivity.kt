package dk.cphbusiness.masterdetailtemplate

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import dk.cphbusiness.opengl.OpenGLES20Activity
import dk.cphbusiness.opengles.OpenGLActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

fun String.times(count: Int) : String {
    var result = this;
    for (i in 2..count) {
        result += this
        }
    return result
    }

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val b = findViewById(R.id.peopleButton) as Button
        }

    fun buttonPressed(view: View) {
        when (view) {
            peopleButton -> {
                toast("--:-- ")
                startActivity(Intent(this, PeopleActivity::class.java))
                }
            serviceButton -> {
                toast("going to service activity")
                startActivity(Intent(this, ServiceStartActivity::class.java))
                }
            openGLButton -> {
                toast("opening graphics activity")
                startActivity(Intent(this, OpenGLES20Activity::class.java))
                }
            openGLESButton -> {
                toast("opening graphics ES activity")
                startActivity(Intent(this, OpenGLActivity::class.java))
            }
            else -> {
                toast("Fetching data...")
//            Is NOT allowed!
//            val peopleJson = URL("http://10.50.162.14:4711/Nice/person").readText()
//            toast(peopleJson)
                async() {
                    PersonRequest(ipText.text.toString()).run()
                    uiThread { toast("... done") }
                    }
                }
            }
        }

    }
