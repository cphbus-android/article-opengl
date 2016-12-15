package dk.kalhauge.opengl

import android.opengl.GLES20

abstract class Shader(val type: Int/*, val code: Code*/) {
    val handle: Int
    val links = mutableListOf<Link>()
    init {
        handle = 7
/*
        handle = GLES20.glCreateShader(type)
        GLES20.glShaderSource(handle, code.raw)
        check("Shader #${handle} source:\n${code.raw}\n")
        GLES20.glCompileShader(handle)
        check("Shader compile")
*/
        }

    //class Code(var raw: String = "")

    fun uniform(name: String, data: Vector4): Link {
        val link = UniformVector(this, name, data)
        links.add(link)
        return link
        }

    fun uniform(name: String, data: Matrix4): Link {
        val link = UniformMatrix(this, name, data)
        links.add(link)
        return link
        }

    val lines = mutableListOf<Code>()

    fun glAssign(name: String, value: Code) {
        lines.add(Assignment(name, value))
        }

    val source: String
        get() =
            links
                .map { it.definition }
                .joinToString(separator = "\n")+
            "\nvoid main() {\n  "+
            lines
                .map { it.text }
                .joinToString(separator = "\n")+
            "\n  }"

    }


class VertexShader(/*code: Code*/) : Shader(GLES20.GL_VERTEX_SHADER/*, code*/) {
    var position: Code
        set(value) { glAssign("gl_Position", value)}
        get() = NoCode()
    fun main(code: VertexShader.() -> Unit) {
        code()
    }

}

class FragmentShader(/*code: Code*/) : Shader(GLES20.GL_FRAGMENT_SHADER/*, code*/) {
    fun main(code: FragmentShader.() -> Unit) {
        code()
    }

}

interface Code {
    val text: String
    }

class NoCode : Code {
    override val text: String
        get() = "Write only property"
    }

class Assignment(val name: String, val code: Code) : Code {
    override val text: String
        get() = "$name = ${code.text};"
    }


abstract class Link(val shader: Shader, val name: String) : Code {
    abstract val definition: String
    override val text: String
        get() = name
    }

class UniformVector(shader: Shader, name: String, val data: Vector4) : Link(shader, name) {
    override val definition: String
        get() = "uniform vec4 ${name};"
    }

class UniformMatrix(shader: Shader, name: String, val data: Matrix4) : Link(shader, name) {
    override val definition: String
        get() = "uniform mat4 ${name};"
    }

class AttributeVector()

class ProductCode(val left: Code, val right: Code) : Code {
    override val text: String
        get() = "${left.text} * ${right.text}"
    }

operator fun Code.times(other: Code) = ProductCode(this, other)
