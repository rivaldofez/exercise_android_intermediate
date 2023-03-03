package com.rivaldofez.simplecanvas

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
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
        mPaint.color = ResourcesCompat.getColor(resources, R.color.teal_700, null)

//        mCanvas.drawCircle((mBitmap.width/2).toFloat(), (mBitmap.height/2).toFloat(), 200f, mPaint)
        mCanvas.drawCircle((mBitmap.width/2).toFloat(), (mBitmap.height/2).toFloat(), 200f, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.teal_200, null)
        mCanvas.drawRect(
            (mBitmap.width/2 - 100).toFloat(),
            (mBitmap.height/2 - 100).toFloat(),
            (mBitmap.width/2 + 100).toFloat(),
            (mBitmap.height/2 + 100).toFloat(),
            mPaint
        )



    }
}

class CanvasView(context: Context): View(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}