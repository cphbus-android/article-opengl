package dk.cphbusiness.masterdetailtemplate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_service_end.*

class ServiceEndActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_end)
        val millis = intent.extras.getLong("millis")
        endServiceMessage.setText("Der gik ${millis} ms")

    }
}
