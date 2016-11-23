package dk.cphbusiness.opengles

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGLSurfaceView(context: Context) : GLSurfaceView(context), GLSurfaceView.Renderer {

    init {
        setEGLContextClientVersion(2)
        setRenderer(this)
        renderMode = RENDERMODE_WHEN_DIRTY
        debugFlags = DEBUG_LOG_GL_CALLS or DEBUG_CHECK_GL_ERROR
        }

    override fun onDrawFrame(unused: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        }

    override fun onSurfaceChanged(unused: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        }

    override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
        //                  red,  green, blue, alpha
        GLES20.glClearColor(0.7f, 0.0f,  0.0f, 1.0f)
        }

    }