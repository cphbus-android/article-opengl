package dk.kalhauge.opengl

import android.opengl.GLES20
import dk.cphbusiness.opengl.COORDS_PER_VERTEX
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class Vertices(data: FloatArray) {
    val buffer: FloatBuffer

    init {
        val bytes = ByteBuffer.allocateDirect(4*data.size).order(ByteOrder.nativeOrder())
        buffer = bytes.asFloatBuffer().put(data)
        buffer.position(0)
        }

    }

class DrawList(val data: ShortArray) {
    val buffer: ShortBuffer

    init {
        val bytes = ByteBuffer.allocateDirect(2*data.size).order(ByteOrder.nativeOrder())
        buffer = bytes.asShortBuffer().put(data)
        buffer.position(0)
        }

    }

