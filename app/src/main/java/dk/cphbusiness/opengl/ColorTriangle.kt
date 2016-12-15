package dk.cphbusiness.opengl

import android.opengl.GLES20
import dk.kalhauge.opengl.*

class ColorTriangle {
    val program = Program()
    val vertices = Vertices(floatArrayOf(
            -0.5f, -0.3f, 0f, 1f, 0f, 0f, 1f,
             0.5f, -0.3f, 0f, 0f, 1f, 0f, 1f,
             0.0f,  0.6f, 0f, 0f, 0f, 1f, 1f
            ))

/*
    private val vertexShaderCode = Shader.Code("""
            |uniform mat4 u_MVPMatrix;
            |attribute vec4 a_Position;
            |attribute vec4 a_Color;
            |varying vec4 v_Color;
            |void main() {
            |  v_Color = a_Color;
            |  gl_Position = u_MVPMatrix * a_Position;
            |  }
            """.trimMargin())

    private val fragmentShaderCode = Shader.Code("""
            |precision mediump float;
            |varying vec4 v_Color;
            |void main() {
            |  gl_FragColor = v_Color;
            |  }
            """.trimMargin())
*/

    init {
//        program.attach(VertexShader(vertexShaderCode))
//        program.attach(FragmentShader(fragmentShaderCode))
        program.link()
        }

    fun draw(mvp: Matrix4) {
        program.use {
            uniform("u_MVPMatrix", mvp)
            attribute("a_Position", vertices, size = 3, stride = 7)
            attribute("a_Color", vertices, offset = 3, size = 4, stride = 7)
            draw(count = 3)
            }
        }

    }