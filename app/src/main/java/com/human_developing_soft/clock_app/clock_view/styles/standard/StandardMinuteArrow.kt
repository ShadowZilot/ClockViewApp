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
class StandardMinuteArrow(
    paint: Paint
) : ClockDrawing(paint) {

    override fun draw(canvas: Canvas,
                      width: Float, height: Float, time: Calendar) {
        canvas.save().apply {
            canvas.rotate(
                6f * time.get(Calendar.MINUTE),
                width/2f,
                height/2f
            )
            canvas.drawLine(width/2f, height/2f,
                width/2f,
                (height /2f) - (height * 0.30f),
                mPaint)
            canvas.restoreToCount(this)
        }
    }
}