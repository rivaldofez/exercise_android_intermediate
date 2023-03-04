package com.rivaldofez.likesapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.rivaldofez.likesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val mCanvas = Canvas(mBitmap)
    private val mPaint = Paint()

    private val halfOfWidth = (mBitmap.width/2).toFloat()
    private val halfOfHeight = (mBitmap.height/2).toFloat()

    private val left = 150F
    private val top = 150F
    private val right = mBitmap.width - left
    private val bottom = mBitmap.height.toFloat() - 150F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setImageBitmap(mBitmap)

        binding.like.setOnClickListener {
            showFace()
            showMouth(isHappy = true)
            showEyes()
        }

        binding.dislike.setOnClickListener {
            showFace()
            showMouth(isHappy = false)
            showEyes()
        }

    }

    private fun showFace() {
        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        mCanvas.drawArc(face, 90F, 180F, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        mCanvas.drawArc(face, 270F, 180F, false, mPaint)
    }

    private fun showEyes() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        mCanvas.drawCircle(halfOfWidth - 100F, halfOfHeight - 100F, 50F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 100F, halfOfHeight - 100F, 50F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        mCanvas.drawCircle(halfOfWidth - 120F, halfOfHeight - 120F, 15F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 80F, halfOfHeight -120F, 15F, mPaint)
    }

    private fun showMouth(isHappy: Boolean) {
        when (isHappy) {
            true -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight - 300F, halfOfWidth + 200F, halfOfHeight + 200F)
                mCanvas.drawArc(lip, 25F, 130F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight -200, halfOfWidth + 180F, halfOfHeight + 180F)
                mCanvas.drawArc(mouth, 25F, 130F, false, mPaint)

            }
            false -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight + 20 , halfOfWidth + 200F, halfOfHeight + 350F)
                mCanvas.drawArc(lip, 0F, -180F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight + 40, halfOfWidth + 180F, halfOfHeight + 300F)
                mCanvas.drawArc(mouth, 0F, -180F, false, mPaint)
            }
        }
    }

}