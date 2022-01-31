package com.human_developing_soft.clock_app.clock_view.styles.standard

import android.graphics.Canvas
import android.graphics.Paint
import com.human_developing_soft.clock_app.clock_view.ClockDrawing
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class StandardClockFace(
    paint: Paint
) : ClockDrawing(paint) {

    override fun draw(canvas: Canvas,
                      width: Float, height: Float, time: Calendar) {
        canvas.drawCircle(width / 2f, height / 2f, (width / 2f) - 5, mPaint)
    }
}