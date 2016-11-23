package dk.cphbusiness.opengl

import android.opengl.GLES20
import dk.kalhauge.opengl.Matrix4
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

val COORDS_PER_VERTEX = 3
val coordinates = floatArrayOf(
        0.00f, 0.62f, 0.00f,
        -0.50f, -0.31f, 0.00f,
        0.50f, -0.31f, 0.00f
        )

class Triangle {
    val vertices: FloatBuffer
    val colorParts = floatArrayOf(0.64f, 0.77f, 0.22f, 0.0f)

    private val vertexShaderCode = """
            |uniform mat4 uMVPMatrix;
            |attribute vec4 vPosition;
            |void main() {
            |  gl_Position = uMVPMatrix * vPosition;
            |  }
            """.trimMargin()

    private val fragmentShaderCode = """
            |precision mediump float;
            |uniform vec4 vColor;
            |void main() {
            |  gl_FragColor = vColor;
            |  }
            """.trimMargin()
    val program: Int

    init {
        val byteBuffer = ByteBuffer.allocateDirect(4*coordinates.size)
        byteBuffer.order(ByteOrder.nativeOrder())

        vertices = byteBuffer.asFloatBuffer()
        vertices.put(coordinates)
        vertices.position(0)

        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        program = GLES20.glCreateProgram()
        GLES20.glAttachShader(program, vertexShader)
        GLES20.glAttachShader(program, fragmentShader)
        GLES20.glLinkProgram(program)
        }

    val vertexCount = coordinates.size/ COORDS_PER_VERTEX
    val vertexStride = 4*COORDS_PER_VERTEX

    fun draw(mvp: Matrix4) = draw(mvp.data)

    fun draw(mvpMatrix: FloatArray) {
        GLES20.glUseProgram(program)
        val positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(
                positionHandle,
                COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertices
                )

        val colorHandle = GLES20.glGetUniformLocation(program, "vColor")
        GLES20.glUniform4fv(colorHandle, 1, colorParts, 0)

        val mvpHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix")
        checkGlError("Triangle glGetUniformLocation")

        GLES20.glUniformMatrix4fv(mvpHandle, 1, false, mvpMatrix, 0)
        checkGlError("Triangle glUniformMatrix4fv")

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
        GLES20.glDisableVertexAttribArray(positionHandle)
        }

    }

