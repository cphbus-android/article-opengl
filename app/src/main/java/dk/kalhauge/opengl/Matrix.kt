package dk.kalhauge.opengl

import android.opengl.Matrix

class Matrix4 {
    val data = FloatArray(16)

    fun copy(): Matrix4 {
        val result = Matrix4()
        for (index in data.indices) result.data[index] = data[index]
        return result
        }

    companion object {
        fun rotation(angle: Float, axis: Vector3): Matrix4 {
            val result = Matrix4()
            Matrix.setRotateM(result.data, 0, angle, axis.x, axis.y, axis.z)
            return result
            }
        fun orientation(eye: Vector3, center: Vector3, up: Vector3): Matrix4 {
            val result = Matrix4()
            Matrix.setLookAtM(
                    result.data, 0,
                    eye.x, eye.y, eye.z,
                    center.x, center.y, center.z,
                    up.x, up.y, up.z)
            return result
            }
        fun frustum(
                left: Float, right: Float,
                bottom: Float, top: Float,
                near: Float, far: Float
                ): Matrix4 {
            val result = Matrix4()
            Matrix.frustumM(
                    result.data, 0,
                    left, right,
                    bottom, top,
                    near, far
                    )
            return result
            }
        }

    operator fun times(matrix: Matrix4): Matrix4 {
        val result = Matrix4()
        Matrix.multiplyMM(result.data, 0, data, 0, matrix.data, 0)
        return result
        }

    operator fun times(vector: Vector4): Vector4 {
        val result = Vector4()
        Matrix.multiplyMV(result.data, 0, data, 0, vector.data, 0)
        return result
        }

    fun rotated(angle: Float, axis: Vector3): Matrix4 {
        val result = copy()
        Matrix.rotateM(result.data, 0, angle, axis.x, axis.y, axis.z)
        return result
        }

    }

open class Vector4 {
    val data = FloatArray(4)
    companion object {
        fun color(red: Float = 0f, green: Float = 0f, blue: Float = 0f, alpha: Float = 1f): Vector4 {
            val result = Vector4()
            result.data[0] = red;
            result.data[1] = green;
            result.data[2] = blue;
            result.data[3] = alpha;
            return result
            }
        }
    }

class Vector3() {
    val data = FloatArray(3)
    val x: Float get() = data[0]
    val y: Float get() = data[1]
    val z: Float get() = data[2]

    constructor(x: Float, y: Float, z: Float) : this() {
        data[0] = x
        data[1] = y
        data[2] = z
        }

    companion object {
        val xAxis = Vector3(1f, 0f, 0f)
        val yAxis = Vector3(0f, 1f, 0f)
        val zAxis = Vector3(0f, 0f, 1f)
        val origo = Vector3(0f, 0f, 0f)
        }

    }

operator fun Float.times(vector: Vector3): Vector3 {
    val result = Vector3()
    for (index in vector.data.indices) result.data[index] = this*vector.data[index]
    return result
    }

operator fun Int.times(vector: Vector3) = this.toFloat().times(vector)

operator fun Vector3.times(factor: Float) = factor.times(this)
