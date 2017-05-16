# OpenGL

To create an openGL activity, first add the feature to the `AndroidManifest.xml` file:
```xml
<uses-feature
    android:glEsVersion="0x00020000"
    android:required="true" />
```
Create an empty (for now) GL surface view class, it should extend the `GLSurfaceView`
it is important to set the client version `2` and it should match `0x00020000` in the manifest.
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
