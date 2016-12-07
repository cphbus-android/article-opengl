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
* Gruppe 5: [Maps](https://github.com/CBorum/kotlin-google-maps)
  - Christopher Borum
  - Kristian Nielsen
  - Mats Krüger
* Gruppe 6: Network
* Gruppe 7: SMS
* Gruppe 8: [Proximity and Database](https://github.com/ElDuderino420/KotlinStuff)
  - David Samuelsen
  - Alexandar Kraunsøe

# Kotlin Features

* Gruppe 1: Extension functions
* Gruppe 2: Delegated properties
* Gruppe 3: Operator overloading
* Gruppe 4: Collections and Ranges
* Gruppe 5: Lambdas
* Gruppe 6: Classes and Interfaces
* Gruppe 7: Properties and Fields
* Gruppe 8: Type Checks and Casts

# Information omkring Android med Kotlin

## Pensum
* Kotlin for Android Developers, Antonio Leiva
  [Leanpub](http://leanpub.com/kotlin-for-android-developers)
  Reference
* Ovenstående artikler
* Android/Kotlin-delen af det fælles projekt
* Anvendte præsentationer (Fronter)

## Referencer
* [Kotlin Language Documentation](https://kotlinlang.org/docs/reference/)
* [Introduction to Android](https://developer.android.com/guide/index.html)

## Eksamen

Til eksamen præsenteres den app, som er udviklet til det fælles projekt. Der er afsat ca. 10 minutter til præsentationen.
Man forventes, ud over præsentationen af selve app'en i funktion, også at kunne fremvise den bagvedliggende kode.
Den resterende del af eksaminationen vil være en diskution af koden, både med hensyn til Android og Kotlin features.
Diskutionen kan også berøre emner fra pensum, som ikke er dækket af projektet. 

Bemærk:
* De dele af app'en man vil bedømmes på, skal være skrevet i **Kotlin**.
* Da projektet i sin helhel først skal afleveres den 5. januar, er det app'en som den ser ud på eksamensdagen, der præsenteres.
* Det er frit for at udvikle videre på app'en efter Android/Kotlin eksamen.

# Eksamensrækkefølge
Alle, undtagen første eksaminant, skal møde minimum 30 minutter før planlagt tid!

Eksamen er onsdag den 21. december 2016 i lokale 1.01

Start | Slut  | Navn
:---: | :---: | ---
08:30 | 09:00 | 
09:00 | 09:30 |
09:30 | 10:00 |
10:00 | 10:30 |
10:30 | 11:00 |
11:00 | 11:30 |
11:30 | 12:00 |
----- | ----- | *Frokost*
12:30 | 13:00 |
13:00 | 13:30 |
13:30 | 14:00 |
14:00 | 14:30 |
14:30 | 15:00 |
15:00 | 15:30 |
15:30 | 16:00 |

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
