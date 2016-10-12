package dk.cphbusiness.masterdetailtemplate

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ExampleService : Service() {
    var millis: Long = 0L

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        millis = System.currentTimeMillis()

        return START_STICKY
        }

    override fun onBind(intent: Intent?): IBinder {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    override fun onDestroy() {
        val timeSpend =  System.currentTimeMillis() - millis
        Thread.sleep(5000)
        val intent = Intent(this, ServiceEndActivity::class.java)
        intent.putExtra("millis", timeSpend)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        }

    }

