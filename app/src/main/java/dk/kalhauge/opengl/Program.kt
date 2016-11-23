package dk.kalhauge.opengl

import android.opengl.GLES20
import android.util.Log
import java.nio.IntBuffer


fun check(message: String) {
    val error = GLES20.glGetError()
    if (error == GLES20.GL_NO_ERROR) Log.i("GLES-CPH", "$message")
    else Log.e("GLES-CPH", "$message: $error")
    //throw GLErrorException(error, message)
    }

class Program {
    val handle = GLES20.glCreateProgram()
    val attributeHandles = mutableListOf<Int>()

    init {
        check("program created with $handle")
        }

    fun attach(shader: Shader) {
        GLES20.glAttachShader(handle, shader.handle)
        check("Attaching shader")
        }

    fun bind(position: Int, name: String) {
        GLES20.glBindAttribLocation(handle, position, name)
        }

    fun link() {
        GLES20.glLinkProgram(handle)
        check("Linking program")
        val linkStatus = IntBuffer.allocate(1)
        GLES20.glGetProgramiv(handle, GLES20.GL_LINK_STATUS, linkStatus)
        val status = linkStatus.get()
        if (status == GLES20.GL_FALSE) {
            GLES20.glDeleteProgram(handle)
            check("linkStatus is (${status})")
            return
            }
        }

    fun use(include: Program.() -> Unit) {
        GLES20.glUseProgram(handle)
        check("Using program")
        include()
        for (attributeHandle in attributeHandles)
                GLES20.glDisableVertexAttribArray(attributeHandle)
        }

    fun draw(offset: Int = 0, count: Int) {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, offset, count)
        }

    fun draw(drawList: DrawList) {
        GLES20.glDrawElements(
                GLES20.GL_TRIANGLES,
                drawList.data.size,
                GLES20.GL_UNSIGNED_SHORT,
                drawList.buffer
                )
        }

    fun uniform(name: String, vector: Vector4) {
        val uniformHandle = GLES20.glGetUniformLocation(handle, name)
        check("Get uniform location of $name with $uniformHandle")
        GLES20.glUniform4fv(uniformHandle, 1, vector.data, 0)
        check("Setting vector")
        }

    fun uniform(name: String, matrix: Matrix4) {
        val uniformHandle = GLES20.glGetUniformLocation(handle, name)
        check("Get uniform location of $name with $uniformHandle")
        GLES20.glUniformMatrix4fv(uniformHandle, 1, false, matrix.data, 0)
        check("Setting matrix")
        }

    fun attribute(
            attributeHandle: Int,
            vertices: Vertices,
            offset: Int = 0, size: Int = 3, stride: Int = 3
            ) {
        Log.i("GLES-CPH", "attribute handle was $attributeHandle")
        vertices.buffer.position(offset)
        GLES20.glEnableVertexAttribArray(attributeHandle)
        GLES20.glVertexAttribPointer(
                attributeHandle,
                size,
                GLES20.GL_FLOAT,
                false,
                4*stride,
                vertices.buffer
                )
        }

    fun attribute(
            name: String,
            vertices: Vertices,
            offset: Int = 0, size: Int = 3, stride: Int = 3
            ) {
        val attributeHandle = GLES20.glGetAttribLocation(handle, name)
        check("Get attribute location of $name with $attributeHandle")
        attributeHandles.add(attributeHandle)
        vertices.buffer.position(offset)
        GLES20.glEnableVertexAttribArray(attributeHandle)
        GLES20.glVertexAttribPointer(
                attributeHandle,
                size,
                GLES20.GL_FLOAT,
                false,
                4*stride,
                vertices.buffer
                )
        }

    }

