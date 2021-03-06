package dk.cphbusiness.opengl

import dk.kalhauge.opengl.*

class Cube {
    val program = Program()
    val vertices = Vertices(floatArrayOf(
            -0.5f, -0.5f, -0.5f,
             0.5f, -0.5f, -0.5f,
             0.5f,  0.5f, -0.5f,
            -0.5f,  0.5f, -0.5f,
            -0.5f, -0.5f,  0.5f,
             0.5f, -0.5f,  0.5f,
             0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f
            ))
    val drawOrder = DrawList(shortArrayOf(
            0, 2, 1, 0, 3, 2,
            5, 1, 2, 5, 2, 6,
            5, 3, 1, 5, 4, 3,
            4, 3, 0, 4, 7, 3,
            4, 5, 6, 4, 6, 7,
            7, 6, 2, 7, 2, 3
            ))
    val color = Vector4.color(blue = 0.7f, red = 0.7f)

    init {
        program.link()
        }

    fun draw(mvp: Matrix4) {
        program.use {
            attribute("vPosition", vertices)
            uniform("vColor", color)
            uniform("uMVPMatrix", mvp)
            draw(drawOrder)
            }
        }
    }