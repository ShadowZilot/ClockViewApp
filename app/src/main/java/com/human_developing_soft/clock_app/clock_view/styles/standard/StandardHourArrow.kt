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
class StandardHourArrow(
    paint: Paint
) : ClockDrawing(paint) {

    override fun draw(canvas: Canvas,
                      width: Float, height: Float, time: Calendar) {
        canvas.save().apply {
            canvas.rotate(
                30f * time.get(Calendar.HOUR),
                width/2f,
                height/2f
            )
            canvas.drawLine(width/2f, height/2f,
                width/2f,
                (height /2f) - (height * 0.25f),
                mPaint)
            canvas.restoreToCount(this)
        }
    }
}