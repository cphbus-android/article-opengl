package dk.cphbusiness.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent

class HelloGLSurfaceView(context: Context) : GLSurfaceView(context) {
    val renderer: SmartGLRenderer
    val TOUCH_SCALE_FACTOR = 180f/320
    var lastX = 0f
    var lastY = 0f

    init {
        setEGLContextClientVersion(2)
        renderer = SmartGLRenderer()
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY
        }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false;
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                val dx = (x - lastX)*(if (x < width/2) -1 else 1)
                val dy = (y - lastY)*(if (y < height/2) -1 else 1)
                renderer.angle = renderer.angle + (dx + dy)*TOUCH_SCALE_FACTOR
                requestRender()
                }
            else -> return true
            }
        lastX = x
        lastY = y
        return true
        }

    }

