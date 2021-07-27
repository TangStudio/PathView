package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import java.io.InputStream

object Tools {
    @JvmStatic
    fun getResBitmapFor8888(context: Context, res: Int): Bitmap? {
        var bitmap: Bitmap? = null
        var `is`: InputStream? = null
        try {
            val opt = BitmapFactory.Options()
            opt.inPreferredConfig = Bitmap.Config.ARGB_8888
            opt.inInputShareable = true
            opt.inPurgeable = true
            `is` = context.resources.openRawResource(res)
            bitmap = BitmapFactory.decodeStream(`is`, null, opt)
        } catch (ignored: Exception) {
        } finally {
            try {
                `is`?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return bitmap
    }

    /**
     * scale bitmap
     * @param bitmap
     * @param scale
     * @return
     */
    @JvmStatic
    @Synchronized
    fun scaleBitmap(bitmap: Bitmap?, scale: Float): Bitmap? {
        var bitmap: Bitmap? = bitmap ?: return null
        val width = bitmap?.width
        val height = bitmap?.height
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        try {
            if (width!! > 0 && height!! > 0) {
                bitmap = bitmap?.let { Bitmap.createBitmap(it, 0, 0, width, height, matrix, true) }
            }
        } catch (e: Exception) {
        }
        return bitmap
    }
}