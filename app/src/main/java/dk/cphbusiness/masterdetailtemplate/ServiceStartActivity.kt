package dk.cphbusiness.masterdetailtemplate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ServiceStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_start)
        }

    fun startServicePressed(view: View) {
        //toast("pressed")
        startService(Intent(this, ExampleService::class.java))
        //toast("started")
        }

    fun stopServicePressed(view: View) {
        //toast("pressed")
        stopService(Intent(this, ExampleService::class.java))
        //toast("started")
    }

}
