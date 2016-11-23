package dk.cphbusiness.opengl

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

val squareCoordinates = floatArrayOf(
        -0.5f, 0.5f, 0f,
        -0.5f, -0.5f, 0f,
        0.5f, -0.5f, 0f,
        0.5f, 0.5f, 0f
        )

class Square {
    val vertices: FloatBuffer
    val drawList: ShortBuffer
    val drawOrder = shortArrayOf( 0, 1, 2, 0, 2, 3 )
    val vertexStride = 4*COORDS_PER_VERTEX
    val colorParts = floatArrayOf(0.2f, 0.709803922f, 0.898039216f, 1.0f)

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

    val programHandle: Int


    init {
        val vertexBytes = ByteBuffer
                .allocateDirect(4*squareCoordinates.size)
                .order(ByteOrder.nativeOrder())
        vertices = vertexBytes.asFloatBuffer().put(squareCoordinates)
        vertices.position(0)

        val drawListBytes = ByteBuffer
                .allocateDirect(2*drawOrder.size)
                .order(ByteOrder.nativeOrder())
        drawList = drawListBytes.asShortBuffer().put(drawOrder)
        drawList.position(0)

        val vertexShaderHandle = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
//        checkGlError("loading vertex shader")
        val fragmentShaderHandle = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
//        checkGlError("loading fragment shader")

        programHandle = GLES20.glCreateProgram()
        GLES20.glAttachShader(programHandle, vertexShaderHandle)
        GLES20.glAttachShader(programHandle, fragmentShaderHandle)
        GLES20.glLinkProgram(programHandle)
        checkGlError("linking program")
        }

    fun draw(matrix: FloatArray) {
        GLES20.glUseProgram(programHandle)
        checkGlError("square program use")

        val positionHandle = GLES20.glGetAttribLocation(programHandle, "vPosition")
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(
                positionHandle,
                COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,
                false,
                vertexStride,
                vertices
                )
        val colorHandle = GLES20.glGetUniformLocation(programHandle, "vColor")
        GLES20.glUniform4fv(colorHandle, 1, colorParts, 0)

        val projectionMatrixHandle = GLES20.glGetUniformLocation(programHandle, "uMVPMatrix")
//        checkGlError("glGetUniformLocation uMVPMatrix")

        GLES20.glUniformMatrix4fv(projectionMatrixHandle, 1, false, matrix, 0)
//        checkGlError("glUniformMatrix4fv")

        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES,
                drawOrder.size,
                GLES20.GL_UNSIGNED_SHORT,
                drawList
                )

        GLES20.glDisableVertexAttribArray(positionHandle)
        }

    }