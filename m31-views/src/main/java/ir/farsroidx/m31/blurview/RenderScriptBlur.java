package ir.farsroidx.m31.blurview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.NonNull;

@Deprecated
public class RenderScriptBlur implements BlurAlgorithm {

    private final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);

    private final RenderScript renderScript;

    private final ScriptIntrinsicBlur blurScript;

    private Allocation outAllocation;

    private int lastBitmapWidth = -1;

    private int lastBitmapHeight = -1;

    public RenderScriptBlur(@NonNull Context context) {
        renderScript = RenderScript.create(context);
        blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
    }

    private boolean canReuseAllocation(@NonNull Bitmap bitmap) {
        return bitmap.getHeight() == lastBitmapHeight && bitmap.getWidth() == lastBitmapWidth;
    }

    @Override
    public Bitmap blur(@NonNull Bitmap bitmap, float blurRadius) {

        Allocation inAllocation = Allocation.createFromBitmap(renderScript, bitmap);

        if (!canReuseAllocation(bitmap)) {

            if (outAllocation != null) {
                outAllocation.destroy();
            }

            outAllocation = Allocation.createTyped(renderScript, inAllocation.getType());
            lastBitmapWidth = bitmap.getWidth();
            lastBitmapHeight = bitmap.getHeight();
        }

        blurScript.setRadius(blurRadius);
        blurScript.setInput(inAllocation);

        blurScript.forEach(outAllocation);
        outAllocation.copyTo(bitmap);

        inAllocation.destroy();

        return bitmap;
    }

    @Override
    public final void destroy() {

        blurScript.destroy();
        renderScript.destroy();

        if (outAllocation != null) outAllocation.destroy();
    }

    @Override
    public boolean canModifyBitmap() {
        return true;
    }

    @NonNull
    @Override
    public Bitmap.Config getSupportedBitmapConfig() {
        return Bitmap.Config.ARGB_8888;
    }

    @Override
    public float scaleFactor() {
        return BlurController.DEFAULT_SCALE_FACTOR;
    }

    @Override
    public void render(@NonNull Canvas canvas, @NonNull Bitmap bitmap) {
        canvas.drawBitmap(bitmap, 0f, 0f, paint);
    }
}
