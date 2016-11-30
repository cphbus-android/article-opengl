# Android Features

* Gruppe 1: Location
  - Ingi
  - Kristoffer
  - Magnus
* Gruppe 2: Kompas
* Gruppe 3: Content providers
* Gruppe 4: [Gyroskop and accelerometer](https://github.com/Thug-Lyfe/Gyro-Acc)
  - Marco S. Blum
  - Christian Lind
* Gruppe 5: Maps
* Gruppe 6: Network
* Gruppe 7: SMS
* Gruppe 8: [Proximity and Database](https://github.com/ElDuderino420/KotlinStuff)
  - David Samuelsen
  - Alexandar Kraunsøe

# Kotlin Features

* Gruppe 1: Extension functions
* Gruppe ?: Delegated properties
* Gruppe ?: Operator overloading
* Gruppe 4: Collections and Ranges
* Gruppe 5: Lambdas
* Gruppe 6: Classes and Interfaces
* Gruppe 7: Properties and Fields
* Gruppe 8: Type Checks and Casts

# OpenGL

To create an openGL activity, first add the feature to the `AndroidManifest.xml` file:
```xml
<uses-feature
    android:glEsVersion="0x00020000"
    android:required="true" />
```
Create an empty (for now) GL surface view class, it should extend the `GLSurfaceView`
```kotlin
import android.opengl.GLSurfaceView

class OpenGLSurfaceView(context: Context) : GLSurfaceView(context) {
    init {
        setEGLContextClientVersion(2)
        // ...
        }
    }
```

Create an empty Activity
```kotlin
package dk.cphbusiness.opengles

import android.app.Activity
import android.os.Bundle

class OpenGLActivity : Activity() {
    lateinit var surfaceView: OpenGLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        surfaceView = OpenGLSurfaceView(this)
        setContentView(surfaceView)
        }

    }
```
