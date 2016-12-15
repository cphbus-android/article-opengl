package dk.cphbusiness.opengles

import android.opengl.GLES20
import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

fun checkGlError(operation: String) {
    val error = GLES20.glGetError()
    if (error == GLES20.GL_NO_ERROR) return
    Log.e("GL rendering", "$operation: #$error")
    // throw RuntimeException("$operation: #$error")
    }

fun loadShader(type: Int, code: String) : Int {
    val shaderHandle = GLES20.glCreateShader(type)
    GLES20.glShaderSource(shaderHandle, code)
    GLES20.glCompileShader(shaderHandle)
    checkGlError("shader compile")
    return shaderHandle
    }

class Triangle {
    val coordinates = floatArrayOf(
        //   x      y      z
            -0.5f, -0.3f,  0.0f, // vertex #0
             0.5f, -0.3f,  0.0f, // vertex #1
             0.0f,  0.5f,  0.0f  // vertex #2
            )
    val colorParts = floatArrayOf(0.64f, 0.77f, 0.22f, 1.0f)

    val vertexShaderCode = """
            |attribute vec4 vPosition;
            |void main() {
            |  gl_Position = vPosition;
            |  }
            """.trimMargin()
    val fragmentShaderCode =
            "precision mediump float;"+
            "uniform vec4 vColor;"+
            "void main() {"+
            "  gl_FragColor = vColor;"+
            "  }"

    val coordinateBuffer: FloatBuffer
    val programHandle: Int

    init {
        val bytes = ByteBuffer.allocateDirect(4*coordinates.size)
        bytes.order(ByteOrder.nativeOrder())
        coordinateBuffer = bytes.asFloatBuffer().put(coordinates)
        coordinateBuffer.position(0)

        val vertexShaderHandle = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShaderHandle = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        programHandle = GLES20.glCreateProgram()
        GLES20.glAttachShader(programHandle, vertexShaderHandle)
        GLES20.glAttachShader(programHandle, fragmentShaderHandle)
        GLES20.glLinkProgram(programHandle)
        }

    fun draw() {
        GLES20.glUseProgram(programHandle)
        // val uMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "uMVPMatrix")
        val vPositionHandle = GLES20.glGetAttribLocation(programHandle, "vPosition")
        val vColorHandle = GLES20.glGetUniformLocation(programHandle, "vColor")

        // GLES20.glUniformMatrix4fv(uMVPMatrixHandle, 1, false, mvpMatrix, 0)

        GLES20.glEnableVertexAttribArray(vPositionHandle)
        GLES20.glVertexAttribPointer(
                vPositionHandle,
                3,
                GLES20.GL_FLOAT,
                false,
                4*3,
                coordinateBuffer
                )

        GLES20.glUniform4fv(vColorHandle, 1, colorParts, 0)

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3)
        GLES20.glDisableVertexAttribArray(vPositionHandle)
        }

    }