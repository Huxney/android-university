package com.example.toolbar

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        backgroundAnimation()
        valueAnimation(animationTextview)
    }

    private fun backgroundAnimation() {
        val objectAnimator = ObjectAnimator.ofObject(
            rootLayout,
            "backgroundColor",
            ArgbEvaluator(),
            ContextCompat.getColor(this, R.color.background_end),
            ContextCompat.getColor(this, R.color.background_start)
        )

        objectAnimator.repeatCount = 1
        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.duration = 2500L
        objectAnimator.start()
    }

    private fun valueAnimation(view: View) {
        val valueAnim = ValueAnimator.ofFloat(0f, 50f)
        valueAnim.duration = 250L
        valueAnim.addUpdateListener { view.translationX = it.animatedValue as Float }
        valueAnim.repeatCount = ValueAnimator.INFINITE
        valueAnim.repeatMode = ValueAnimator.REVERSE
        valueAnim.start()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}