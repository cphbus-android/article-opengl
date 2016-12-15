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

/*
public class Person {
    private static int nextId = 0;
    private int id;
    private String name;
    public Person(String name) {
        this.name = name;
        this.id = nextId++
        }
    }
*/

//class Person(var name: String) {
//    val id: Int
//    companion object {
//        /*private*/ var nextId = 0
//        }
//    object awesomeSingleton {
//        var text: String = "Hello"
//        }
//    init {
//        id = nextId++
//        }
//    }
//
//fun xxx() {
//    Person.awesomeSingleton.text
//    Person.nextId
//
//    }
