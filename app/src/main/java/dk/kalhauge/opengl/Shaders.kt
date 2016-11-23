package dk.kalhauge.opengl

import android.opengl.GLES20

abstract class Shader(val type: Int, val code: Code) {
    val handle: Int
    init {
        handle = GLES20.glCreateShader(type)
        GLES20.glShaderSource(handle, code.raw)
        check("Shader #${handle} source:\n${code.raw}\n")
        GLES20.glCompileShader(handle)
        check("Shader compile")
        }

    class Code(var raw: String = "")

    }

class VertexShader(code: Code) : Shader(GLES20.GL_VERTEX_SHADER, code)

class FragmentShader(code: Code) : Shader(GLES20.GL_FRAGMENT_SHADER, code)

