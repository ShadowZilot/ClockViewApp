package com.human_developing_soft.clock_app.clock_view.styles.standard

import android.graphics.*
import androidx.core.graphics.applyCanvas
import com.human_developing_soft.clock_app.clock_view.ClockDrawing
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class StandardClockFace(
    paint: Paint,
    private val mClockStrokeColor: Int
) : ClockDrawing(paint) {
    private var mBitmap : Bitmap? = null

    override fun draw(
        canvas: Canvas,
        width: Float, height: Float, time: Calendar
    ) {
        if (mBitmap == null) {
            mBitmap = Bitmap.createBitmap(width.toInt(),
                height.toInt(), Bitmap.Config.RGB_565)
        }
        mBitmap?.applyCanvas {
            this.drawCircle(width / 2f, height / 2f, (width / 2f) - 5, mPaint)
            mPaint.style = Paint.Style.STROKE
            mPaint.color = mClockStrokeColor
            this.drawCircle(width / 2f, height / 2f, (width / 2f) - 5, mPaint)
        }
        canvas.drawBitmap(mBitmap!!, 0f, 0f, mPaint)
    }
}