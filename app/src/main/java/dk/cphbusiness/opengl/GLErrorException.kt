package dk.cphbusiness.opengl

import android.util.Log

class GLErrorException(val error: Int, message: String) : RuntimeException(message) {

    init {
        Log.e("GL Error", "$message: $error")
        }

    }