package dk.cphbusiness.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class HelloGLRenderer : GLSurfaceView.Renderer {
    var angle = 0f
    private lateinit var triangle: Triangle
    private lateinit var square: Square

    private val mvpMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)
    private val rotationMatrix = FloatArray(16)

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(0.7f, 0f, 0f, 1f)
        triangle = Triangle()
        square = Square()
        }

    override fun onDrawFrame(gl: GL10?) {
        val scratch = FloatArray(16)

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        Matrix.setLookAtM(
                viewMatrix, 0,
                0f, 0f, -3f, // eye x, y, z
                0f, 0f, 0f,  // center x, y, z
                0f, 1f, 0f   // up x, y, z
                )

        Matrix.multiplyMM(
                mvpMatrix, 0, // result
                projectionMatrix, 0, // argument 1
                viewMatrix, 0 // argument 2
                )
        square.draw(mvpMatrix)

        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, 1f)
        Matrix.multiplyMM(scratch, 0, mvpMatrix, 0, rotationMatrix, 0)

        triangle.draw(scratch)

        }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        val ratio = width.toFloat()/height.toFloat()
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
        }

    }

fun loadShader(type: Int, code: String) : Int {
    val shader = GLES20.glCreateShader(type)
    GLES20.glShaderSource(shader, code)
    GLES20.glCompileShader(shader)
    return shader
    }

fun checkGlError(operation: String) {
    val error = GLES20.glGetError()
    if (error == GLES20.GL_NO_ERROR) return
    Log.e("GL rendering", "$operation: #$error")
    throw RuntimeException("$operation: #$error")
    }
