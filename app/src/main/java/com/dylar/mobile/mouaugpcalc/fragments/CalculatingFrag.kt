package com.dylar.mobile.mouaugpcalc.fragments

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.dylar.mobile.mouaugpcalc.R
import com.john.waveview.WaveView
import kotlinx.android.synthetic.main.calculating_scene.*

class CalculatingFrag : Fragment() {
    var calculatedGp = 0.0f
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calculating_scene, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }


    private fun initUI(): Unit {
        val gradient = view?.findViewById<WaveView>(R.id.course_waveView)?.background as GradientDrawable
        val animator = ValueAnimator.ofFloat(0.00f, calculatedGp).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 10000
            addUpdateListener {
                val value = it.animatedValue as Float
                val progress = (value * 80)/ 5.00
                course_waveView?.setProgress(progress.toInt())
                animating_gp_score.text = String.format("%.2f", it.animatedValue)
                if(value in 4.5..5.0) gradient.setColor(Color.parseColor("#4CAF50"))
                if (value in 3.5..4.4) gradient.setColor(Color.parseColor("#9C27B0"))
                if (value in 2.5..3.4) gradient.setColor(Color.parseColor("#FFEB3B"))
                if (value < 2.4) gradient.setColor(Color.parseColor("#F44336"))
            }
            startDelay = 1000
        }

        animator.start()

        calculating_close_button.setOnClickListener {
            animator.cancel()
            animator.end()
            activity!!.supportFragmentManager.popBackStack()
        }
    }
}