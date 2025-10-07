package ir.farsroidx.m31.blurview

import android.graphics.Bitmap
import android.graphics.Canvas

interface BlurAlgorithm {

    val supportedBitmapConfig: Bitmap.Config

    fun blur(bitmap: Bitmap, blurRadius: Float): Bitmap?

    fun destroy()

    fun canModifyBitmap(): Boolean

    fun scaleFactor(): Float

    fun render(canvas: Canvas, bitmap: Bitmap)
}
