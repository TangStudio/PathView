package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.myapplication.Tools.getResBitmapFor8888
import com.example.myapplication.Tools.scaleBitmap

/**
 * @Author: Administrator
 * @Time: 2021/4/14 10:19
 * @Company：ch
 * @Description: 功能描述
 */
class LineView @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    mContext, attrs, defStyleAttr
) {
    private var mWidth = 0
    private var mHeight = 0
    private var pathLine1: Path = Path()
    private var pathLine2: Path = Path()
    private var pathLine3: Path = Path()
    private var pathLine4: Path = Path()
    private var pathLine5: Path = Path()
    private var pointBlack: Paint = Paint()
    private var pointYellow: Paint = Paint()
    private var pointRed: Paint = Paint()
    private var pointBlue: Paint = Paint()
    private var pointGreen: Paint = Paint()
    private val backGroundColor = Color.parseColor("#ffffff")
    private val lineWidth = 5f
    private val scale = 0.3f
    private var x1 = 0f
    private var y1 = 0f
    private var x2 = 0f
    private var x3 = 0f
    private var x4 = 0f
    private var x5 = 0f
    private var y2 = 0f
    private var y3 = 0f
    private var y4 = 0f
    private var y5 = 0f
    private val bitmap1: Bitmap?
    private val bitmap2: Bitmap?
    private val bitmap3: Bitmap?
    private val bitmap4: Bitmap?
    private val bitmap5: Bitmap?
    private var canvas: Canvas? = null
    fun setFirstXY1(x: Float, y: Float) {
        pathLine1.moveTo(x, y)
        x1 = x
        y1 = y
    }

    fun setFirstXY2(x: Float, y: Float) {
        pathLine2.moveTo(x, y)
        x2 = x
        y2 = y
    }

    fun setFirstXY3(x: Float, y: Float) {
        pathLine3.moveTo(x, y)
        x3 = x
        y3 = y
    }

    fun setFirstXY4(x: Float, y: Float) {
        pathLine4.moveTo(x, y)
        x4 = x
        y4 = y
    }

    fun setFirstXY5(x: Float, y: Float) {
        pathLine5.moveTo(x, y)
        x5 = x
        y5 = y
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (changed) {
            mWidth = width
            mHeight = height
            setBackgroundColor(backGroundColor)
            background = resources.getDrawable(R.mipmap.playground)
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    fun drawLine(x: Float, y: Float, id: String?) {
        when (id) {
            "0" -> {
                pathLine1.lineTo(x, y)
                x1 = x
                y1 = y
            }
            "1" -> {
                pathLine2.lineTo(x, y)
                x2 = x
                y2 = y
            }
            "2" -> {
                pathLine3.lineTo(x, y)
                x3 = x
                y3 = y
            }
            "3" -> {
                pathLine4.lineTo(x, y)
                x4 = x
                y4 = y
            }
            "4" -> {
                pathLine5.lineTo(x, y)
                x5 = x
                y5 = y
            }
        }
        postInvalidate()
    }

    fun clearCanvas() {
        val paint = Paint()
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        canvas!!.drawPaint(paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        canvas.drawPath(pathLine1, pointBlack)
        canvas.drawPath(pathLine2, pointYellow)
        canvas.drawPath(pathLine3, pointRed)
        canvas.drawPath(pathLine4, pointBlue)
        canvas.drawPath(pathLine5, pointGreen)

        canvas.drawBitmap(bitmap1!!, x1, y1 - 10, pointBlack)
        canvas.drawBitmap(bitmap2!!, x2, y2 - 10, pointYellow)
        canvas.drawBitmap(bitmap3!!, x3, y3 - 10, pointRed)
        canvas.drawBitmap(bitmap4!!, x4, y4 - 10, pointBlue)
        canvas.drawBitmap(bitmap5!!, x5, y5 - 10, pointGreen)
    }

    init {
        pointBlack.isAntiAlias = true
        pointBlack.strokeWidth = lineWidth
        pointBlack.style = Paint.Style.STROKE
        pointBlack.color = Color.BLACK
        pointYellow.isAntiAlias = true
        pointYellow.strokeWidth = lineWidth
        pointYellow.style = Paint.Style.STROKE
        pointYellow.color = Color.YELLOW
        pointRed.isAntiAlias = true
        pointRed.strokeWidth = lineWidth
        pointRed.style = Paint.Style.STROKE
        pointRed.color = Color.RED
        pointBlue.isAntiAlias = true
        pointBlue.strokeWidth = lineWidth
        pointBlue.style = Paint.Style.STROKE
        pointBlue.color = Color.BLUE
        pointGreen.isAntiAlias = true
        pointGreen.strokeWidth = lineWidth
        pointGreen.style = Paint.Style.STROKE
        pointGreen.color = Color.GREEN
        bitmap1 = scaleBitmap(getResBitmapFor8888(context, R.mipmap.player1), scale)
        bitmap2 = scaleBitmap(getResBitmapFor8888(context, R.mipmap.player2), scale)
        bitmap3 = scaleBitmap(getResBitmapFor8888(context, R.mipmap.player3), scale)
        bitmap4 = scaleBitmap(getResBitmapFor8888(context, R.mipmap.player4), scale)
        bitmap5 = scaleBitmap(getResBitmapFor8888(context, R.mipmap.player5), scale)
    }
}