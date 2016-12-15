package dk.kalhauge.opengl

fun main(arguments: Array<String>) {
    val shader = VertexShader()
    val mvp = Matrix4()
    val colorParts = Vector4.color(red = 0.8f)

    val uMVPMatrix = shader.uniform("uMVPMatrix", mvp)
    val uColor = shader.uniform("uColor", colorParts)
    shader.main() {
        position = uMVPMatrix * uColor
        }




    println(shader.source)

    }