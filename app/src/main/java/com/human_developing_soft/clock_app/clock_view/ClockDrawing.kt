package com.human_developing_soft.clock_app.clock_view

import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
abstract class ClockDrawing(
    protected val mPaint : Paint
) {
    abstract fun draw(canvas: Canvas, width: Float, height: Float, time: Calendar)
}