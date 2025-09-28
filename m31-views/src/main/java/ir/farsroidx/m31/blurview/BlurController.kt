package ir.farsroidx.m31.blurview

import android.graphics.Canvas

interface BlurController : BlurViewFacade {

    fun draw(canvas: Canvas?): Boolean

    fun updateBlurViewSize()

    fun destroy()

    companion object {

        const val DEFAULT_SCALE_FACTOR: Float = 6f

        const val DEFAULT_BLUR_RADIUS: Float = 16f

    }
}
