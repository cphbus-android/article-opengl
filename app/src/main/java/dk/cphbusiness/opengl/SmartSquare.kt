package dk.cphbusiness.opengl

import android.opengl.GLES20
import dk.kalhauge.opengl.*

class SmartSquare {
    val program = Program()
    val vertices = Vertices(floatArrayOf(
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
            ))
    val drawList = DrawList(shortArrayOf( 0, 1, 2, 0, 2, 3 ))

//    val vertexStride = 4*COORDS_PER_VERTEX
    val color = Vector4.color(0.2f, 0.709803922f, 0.898039216f)
//    val color = Vector4.color(red = 1f, green = 1f)

/*
    private val vertexShaderCode = Shader.Code("""
            |uniform mat4 uMVPMatrix;
            |attribute vec4 vPosition;
            |void main() {
            |  gl_Position = uMVPMatrix * vPosition;
            |  }
            """.trimMargin())
*/
/*
    private val vertexShaderCode = Shader.Code(
            "uniform mat4 uMVPMatrix;"+
            "attribute vec4 vPosition;"+
            "void main() {"+
            "  gl_Position = uMVPMatrix * vPosition;"+
            "  }")

    private val fragmentShaderCode = Shader.Code("""
            |precision mediump float;
            |uniform vec4 vColor;
            |void main() {
            |  gl_FragColor = vColor;
            |  }
            """.trimMargin())
*/



    init {
/*
        program.attach(VertexShader(vertexShaderCode))
        program.attach(FragmentShader(fragmentShaderCode))
*/
        program.link()
        }

    fun draw(mvp: Matrix4) {
        program.use {
            attribute("vPosition", vertices)
            uniform("vColor", color)
            uniform("uMVPMatrix", mvp)
            draw(drawList)
            }
        }

}