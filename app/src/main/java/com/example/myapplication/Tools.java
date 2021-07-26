package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.InputStream;

public class Tools {

    /***
     * 解析图片
     *
     * @param context
     *            上下文
     * @param res
     *            图片资源
     */
    public static Bitmap getResBitmapFor8888(Context context, int res)
    {
        Bitmap bitmap = null;
        InputStream is = null;
        try
        {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            // 设置只是解码图片的边距，此操作目的是度量图片的实际宽度和高度
            opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            opt.inInputShareable = true;
            opt.inPurgeable = true;// 设置图片可以被回收
            is = context.getResources().openRawResource(res);
            bitmap =  BitmapFactory.decodeStream(is, null, opt);
        }
        catch (Exception ignored)
        {
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 按比例缩放图片
     *
     * @param bitmap
     * @param scale
     * @return
     */
    public synchronized static Bitmap scaleBitmap(Bitmap bitmap, float scale)
    {
        if (bitmap == null)
        {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        try {
            if (width > 0 && height > 0)
            {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            }
        }
        catch (Exception e)
        {

        }
        return bitmap;
    }
}
