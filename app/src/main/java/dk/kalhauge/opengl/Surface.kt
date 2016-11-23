package dk.kalhauge.opengl

import android.opengl.GLES20

class Surface(val width: Int, val height: Int) {
    val ratio: Float by lazy { width.toFloat()/height.toFloat() }
    init {
        GLES20.glViewport(0, 0, width, height)

        }

    fun clear(color: Vector4 = Vector4()) {
        GLES20.glClearColor(color.data[0], color.data[1], color.data[2], color.data[3])
        }

    fun clear(mask: Int) {
        GLES20.glClear(mask)
        }

    }