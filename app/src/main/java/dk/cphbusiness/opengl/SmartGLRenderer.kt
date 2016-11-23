package dk.cphbusiness.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import dk.kalhauge.opengl.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class SmartGLRenderer : GLSurfaceView.Renderer {
    var angle = 0f
//    private lateinit var triangle: Triangle
//    private lateinit var square: SmartSquare
    private lateinit var cube: Cube
//    private lateinit  var colorTriangle: ColorTriangle

    private var projection = Matrix4()
    private var surface = Surface(0, 0)

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        surface.clear(Vector4.color(red = 0.7f))
//        triangle = Triangle()
//        square = SmartSquare()
        cube = Cube()
//        colorTriangle = ColorTriangle()
        }

    override fun onDrawFrame(gl: GL10?) {
        surface.clear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        val view = Matrix4.orientation(-3*Vector3.zAxis, Vector3.origo, Vector3.yAxis)
        val mvp = projection*view
//        square.draw(mvp)
//
//        triangle.draw(mvp.rotated(angle, Vector3.zAxis))
        cube.draw(mvp.rotated(angle, Vector3.yAxis))
//        colorTriangle.draw(mvp.rotated(angle, Vector3.zAxis))
        }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        surface = Surface(width, height)
        projection = Matrix4.frustum(-surface.ratio, surface.ratio, -1f, 1f, 1f, 10f)
        }

}
