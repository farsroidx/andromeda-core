@file:Suppress("DEPRECATION", "unused")

package ir.farsroidx.m31.additives

import android.app.Activity
import android.os.Build
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.farsroidx.m31.blurview.BlurView
import ir.farsroidx.m31.blurview.RenderEffectBlur
import ir.farsroidx.m31.blurview.RenderScriptBlur

fun Fragment.initializeBlurView(
    parentView: ViewGroup,
    blurView: BlurView,
    radius: Float,
    isBlurEnabled: Boolean = true,
    isAutoUpdateEnabled: Boolean = true
) = requireActivity().initializeBlurView(
    parentView, blurView, radius, isBlurEnabled, isAutoUpdateEnabled
)

fun Activity.initializeBlurView(
    parentView: ViewGroup,
    blurView: BlurView,
    radius: Float,
    isBlurEnabled: Boolean = true,
    isAutoUpdateEnabled: Boolean = true
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

        blurView.setupWith(parentView, RenderEffectBlur())
            .setFrameClearDrawable(parentView.background)
            .setBlurEnabled(isBlurEnabled)
            .setBlurAutoUpdate(isAutoUpdateEnabled)
            .setBlurRadius(radius)

    } else {

        blurView.setupWith(parentView, RenderScriptBlur(applicationContext))
            .setFrameClearDrawable(parentView.background)
            .setBlurEnabled(isBlurEnabled)
            .setBlurAutoUpdate(isAutoUpdateEnabled)
            .setBlurRadius(radius)
    }
}