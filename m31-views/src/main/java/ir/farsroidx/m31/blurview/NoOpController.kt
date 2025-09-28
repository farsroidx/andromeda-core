package ir.farsroidx.m31.blurview

import android.graphics.Canvas
import android.graphics.drawable.Drawable

class NoOpController : BlurController {

    override fun draw(canvas: Canvas?): Boolean = true

    override fun updateBlurViewSize() {}

    override fun destroy() {}

    override fun setBlurRadius(radius: Float): BlurViewFacade = this

    override fun setOverlayColor(overlayColor: Int): BlurViewFacade = this

    override fun setFrameClearDrawable(frameClearDrawable: Drawable?): BlurViewFacade = this

    override fun setBlurEnabled(enabled: Boolean): BlurViewFacade = this

    override fun setBlurAutoUpdate(enabled: Boolean): BlurViewFacade = this

}
