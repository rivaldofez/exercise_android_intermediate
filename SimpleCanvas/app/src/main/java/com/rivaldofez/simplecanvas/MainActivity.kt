package com.rivaldofez.simplecanvas

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(CanvasView(this))

        val myImageView = findViewById<ImageView>(R.id.myImageView)

        val mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        myImageView.setImageBitmap(mBitmap)

        val mCanvas = Canvas(mBitmap)
        mCanvas.drawColor(ResourcesCompat.getColor(resources, R.color.purple_500, null))

        val mPaint = Paint()

        mPaint.color = ResourcesCompat.getColor(resources, R.color.teal_200, null)
        val mRect = Rect()
        mRect.set((mBitmap.width/2 - 100),
            (mBitmap.height/2 - 100),
            (mBitmap.width/2 + 100),
            (mBitmap.height/2 + 100))

//        mCanvas.drawRect(
//            mRect,
//            mPaint
//        )

        mCanvas.clipRect(
            mRect
        )

        mPaint.color = ResourcesCompat.getColor(resources, R.color.teal_700, null)
        mCanvas.drawCircle((mBitmap.width/2).toFloat(), (mBitmap.height/2).toFloat(), 200f, mPaint)


        val mPaintText =  Paint(Paint.FAKE_BOLD_TEXT_FLAG)
        mPaintText.textSize = 20F
        mPaintText.color = ResourcesCompat.getColor(resources, R.color.white, null)

        val text = "Selamat Datang!"
        val mBounds = Rect()
        mPaintText.getTextBounds(text, 0, text.length, mBounds)

        val x: Int = mBitmap.width/2 - mBounds.centerX()
        val y: Int = mBitmap.height/2 - mBounds.centerY()

        mCanvas.drawText(text, x.toFloat(), y.toFloat(), mPaintText)

    }
}

class CanvasView(context: Context): View(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}