# Android Features

* Gruppe 1: [Location](https://github.com/xNoga/LocationKotlin)
  - Ingi Jansson Müller
  - Kristoffer Noga
  - Magnus Illum Honoré Larsen 
* Gruppe 2: Kompas
* Gruppe 3: Content providers
* Gruppe 4: [Gyroskop and accelerometer](https://github.com/Thug-Lyfe/Gyro-Acc)
  - Marco S. Blum
  - Christian Lind
* Gruppe 5: [Maps](https://github.com/CBorum/kotlin-google-maps)
  - Christopher Borum
  - Kristian Nielsen
  - Mats Krüger
* Gruppe 6: [Network](https://github.com/kasperpagh/Android-Kotlin-article)
  - Pelle Carlsen
  - Kasper Roland Pagh
* Gruppe 7: [Send and receive SMS on android](https://github.com/thadino/sms_recieve)
  - Sebastian Ryberg
  - Steffen Lefort
* Gruppe 8: [Proximity and Database](https://github.com/ElDuderino420/KotlinStuff)
  - David Samuelsen
  - Alexandar Kraunsøe

# Kotlin Features
* Gruppe 1: [Extension functions](https://github.com/xNoga/ExtensionFunctions)
* Gruppe 2: Delegated properties
* Gruppe 3: Operator overloading
* Gruppe 4: [Collections and Ranges](https://github.com/Thug-Lyfe/kotlinCollections)
* Gruppe 5: [Lambdas](https://github.com/xNoga/LocationKotlin)
* Gruppe 6: [Classes and Interfaces](https://github.com/kasperpagh/KotlinClassesAndInterfaces/tree/master)
* Gruppe 7: [Properties and Fields](https://github.com/thadino/PropertiesAndFields)
* Gruppe 8: [Type Checks and Casts](https://github.com/Invictus420/TypeChecksandCastsKotlin)

# Information omkring Android med Kotlin

## Pensum
* &#x1F4D8; Kotlin for Android Developers, Antonio Leiva
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
Alle, undtagen første eksaminant, skal møde minimum **_30 minutter_** før planlagt tid!

Eksamen er tirsdag den 20. og onsdag den 21. december 2016 i lokale 1.01

## Tirsdag den 20. december 2016
Start | Slut  | Navn                                  | Grp
:---: | :---: | ------------------------------------- | :---:
08:30 | 09:00 | Blum, Marco Schäffer                  | 4AK
09:00 | 09:30 | **Larsen, Magnus Illum Honoré**       | 1AK
09:30 | 10:00 | **Noga, Kristoffer**                  | 1AK
10:00 | 10:30 | **Müller, Ingi Jansson**              | 1AK
10:30 | 11:00 | **Nielsen, Kristian Oliver Glumsøe**  | 5AK
11:00 | 11:30 | **Krüger, Mats Ulrik**                | 5AK
11:30 | 12:00 | **Borum, Christopher**                | 5AK

## Onsdag den 21. december 2016
Start | Slut  | Navn                                  | Grp
:---: | :---: | ------------------------------------- | :---:
08:30 | 09:00 | Lind, Christian                       | 4AK
09:00 | 09:30 | Carlsen, Pelle Lønberg                | 6AK
09:30 | 10:00 | Lefort, Steffen                       | 7AK
10:00 | 10:30 | Kraunsøe, Alexandar Johan             | 8AK
10:30 | 11:00 | Samuelsen, David Mikael Blum          | 8AK
11:00 | 11:30 | Ryberg, Sebastian                     | 7AK
11:30 | 12:00 | Mortensen, Bo Vilstrup                | 3AK
----- | ----- | *Frokost*                             |
12:30 | 13:00 | **Routhe, Ib Henrik**                 | 3AK
13:00 | 13:30 | **Skogemann, Thomas**                 | 3AK
13:30 | 14:00 | Pagh, Kasper Roland                   | 6AK
```
Szigethy, Jonathan
Redder, Thomas
Saleh, Haider
Szkudlarek, Peter Steen
L., R.
Michael R.
```

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
