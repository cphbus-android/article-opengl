package dk.cphbusiness.opengl

import dk.kalhauge.opengl.*

class ColorTriangle {
    val program = Program()
    val vertices = Vertices(floatArrayOf(
            -0.5f, -0.3f, 0f, 1f, 0f, 0f, 1f,
             0.5f, -0.3f, 0f, 0f, 1f, 0f, 1f,
             0.0f,  0.6f, 0f, 0f, 0f, 1f, 1f
        ))
    private val vertexShaderCode = Shader.Code("""
            |uniform mat4 u_mvp;
            |attribute vec4 a_position;
            |attribute vec4 a_color;
            |varying vec4 v_color;
            |void main() {
            |  v_color = a_color;
            |  gl_Position = u_mvp * a_position;
            |  }
            """.trimMargin())
    private val fragmentShaderCode = Shader.Code("""
            |precision mediump float;
            |varying vec4 v_color;
            |void main() {
            |  gl_FragColor = v_Color;
            |  }
            """.trimMargin())
    init {
        program.attach(VertexShader(vertexShaderCode))
        program.attach(FragmentShader(fragmentShaderCode))
        program.bind(0, "a_position")
        program.bind(1, "a_color")
        program.link()
        }

    fun draw(mvp: Matrix4) {
        program.use {
            uniform("u_mvp", mvp)
            attribute("a_position", vertices, size = 3, stride = 7)
            attribute("a_color", vertices, offset = 3, size = 4, stride = 7)
            draw(count = 3)
            }
        }

    }