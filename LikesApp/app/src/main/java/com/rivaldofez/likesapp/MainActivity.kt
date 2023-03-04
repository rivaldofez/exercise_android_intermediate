package com.rivaldofez.likesapp

import android.graphics.*
import android.os.Build
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

    private val message = "Apakah kamu suka bermain?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setImageBitmap(mBitmap)
        showText()


        binding.like.setOnClickListener {
            showEars()
            showFace()
            showMouth(isHappy = true)
            showEyes()
            showNose()
            showHair()

        }

        binding.dislike.setOnClickListener {
            showEars()
            showFace()
            showMouth(isHappy = false)
            showEyes()
            showNose()
            showHair()

        }

    }

    private fun showFace() {
        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        mCanvas.drawArc(face, 90F, 180F, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        mCanvas.drawArc(face, 270F, 180F, false, mPaint)
    }

    private fun showHair() {
        mCanvas.save()

        val path = Path()

        path.addCircle(halfOfWidth - 100F,halfOfHeight - 100F, 150F, Path.Direction.CCW)
        path.addCircle(halfOfWidth + 100F,halfOfHeight - 100F, 150F, Path.Direction.CCW)

        val mouth = RectF(halfOfWidth - 250F, halfOfHeight, halfOfWidth + 250F, halfOfHeight + 500F)
        path.addOval(mouth, Path.Direction.CCW)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            mCanvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            mCanvas.clipOutPath(path)
        }

        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        mCanvas.drawArc(face, 90F, 180F, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        mCanvas.drawArc(face, 270F, 180F, false, mPaint)

        mCanvas.restore()
    }


    private fun showEyes() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        mCanvas.drawCircle(halfOfWidth - 100F, halfOfHeight - 100F, 50F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 100F, halfOfHeight - 100F, 50F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        mCanvas.drawCircle(halfOfWidth - 120F, halfOfHeight - 120F, 15F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 80F, halfOfHeight -120F, 15F, mPaint)
    }

    private fun showText() {
        val mPaintText = Paint(Paint.FAKE_BOLD_TEXT_FLAG).apply {
            textSize = 50F
            color = ResourcesCompat.getColor(resources, R.color.black, null)
        }

        val mBounds = Rect()
        mPaintText.getTextBounds(message, 0, message.length, mBounds)

        val x: Float = halfOfWidth - mBounds.centerX()
        val y = 50F
        mCanvas.drawText(message, x, y, mPaintText)
    }

    private fun showNose() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        mCanvas.drawCircle(halfOfWidth - 40F, halfOfHeight + 50F, 15F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 40F, halfOfHeight + 50F, 15F, mPaint)
    }

    private fun showEars() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        mCanvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 150F, 100F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        mCanvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 150F, 100F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.red_ear, null)
        mCanvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 150F, 60F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 150F, 60F, mPaint)
    }

    private fun showMouth(isHappy: Boolean) {
        when (isHappy) {
            true -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight - 150F, halfOfWidth + 200F, halfOfHeight + 300F)
                mCanvas.drawArc(lip, 25F, 130F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight - 50 , halfOfWidth + 180F, halfOfHeight + 280F)
                mCanvas.drawArc(mouth, 25F, 130F, false, mPaint)

            }
            false -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight + 100 , halfOfWidth + 200F, halfOfHeight + 350F)
                mCanvas.drawArc(lip, 0F, -180F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight + 120, halfOfWidth + 180F, halfOfHeight + 300F)
                mCanvas.drawArc(mouth, 0F, -180F, false, mPaint)
            }
        }
    }

}