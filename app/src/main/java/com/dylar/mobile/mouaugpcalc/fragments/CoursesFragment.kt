package com.dylar.mobile.mouaugpcalc.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.dylar.mobile.mouaugpcalc.Interfaces.IAdapterUser
import com.dylar.mobile.mouaugpcalc.MainActivity
import com.dylar.mobile.mouaugpcalc.R
import com.dylar.mobile.mouaugpcalc.utils.CourseData
import com.dylar.mobile.mouaugpcalc.utils.CustomViewHolder
import com.dylar.mobile.mouaugpcalc.utils.LevelAdapter
import com.john.waveview.WaveView
import kotlinx.android.synthetic.main.course_frag.*

class CoursesFragment : Fragment(), IAdapterUser {
    override fun adapterCreateView(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_view, parent, false)
        return CustomViewHolder(view)
    }

    override fun adapterOnBindVIew(viewHolder: RecyclerView.ViewHolder, p0: Int) {
        val view = viewHolder.itemView
        view.findViewById<TextView>(R.id.course_view_level_name).text = datacontainer[p0].courseName
        view.findViewById<TextView>(R.id.course_grade).text = datacontainer[p0].grade
        when(datacontainer[p0].grade) {
            "A" -> {
                val gradient = view.findViewById<WaveView>(R.id.course_waveView).background as GradientDrawable
                gradient.setColor(Color.parseColor("#4CAF50"))
            }
            "B" -> {
                val gradient = view.findViewById<WaveView>(R.id.course_waveView).background as GradientDrawable
                gradient.setColor(Color.parseColor("#9C27B0"))
            }
            "C" -> {
                val gradient = view.findViewById<WaveView>(R.id.course_waveView).background as GradientDrawable
                gradient.setColor(Color.parseColor("#CDDC39"))
            }
            "D" -> {
                val gradient = view.findViewById<WaveView>(R.id.course_waveView).background as GradientDrawable
                gradient.setColor(Color.parseColor("#FFEB3B"))
            }
            "F" -> {
                val gradient = view.findViewById<WaveView>(R.id.course_waveView).background as GradientDrawable
                gradient.setColor(Color.parseColor("#F44336"))

            }
            else -> {
                view.findViewById<TextView>(R.id.course_grade).text = "F"
                val gradient = view.findViewById<WaveView>(R.id.course_waveView).background as GradientDrawable
                gradient.setColor(Color.parseColor("#4CAF50"))
            }
        }

        view.setOnLongClickListener {
            highlight(it)
            showDeleteBtn(it)
            view.tag = true
            true
        }
    }

    override fun adapterGetCount(): Int = datacontainer.size

    override fun adapterGetItemType(pos: Int): Int = 0

    private var vibrator: Vibrator? = null
    private val datacontainer = mutableListOf<CourseData>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.course_frag, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).switchOffFAB()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }
    private fun initUI(): Unit {
        // switch fab
        initVibrator()
        (activity as MainActivity).changeToolBarTitle("Courses")
        create_course_btn?.setOnClickListener {
            val dialog = CourseDialog()
            dialog.setPostiveBtnCLicked { _, data ->
                @Suppress("UNCHECKED_CAST")
                val pair = data as CourseData
                datacontainer.add(pair)
                render()
            }
            dialog.show(activity!!.supportFragmentManager, "course_dialog")
        }
        create_course_btn?.setOnLongClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(500)
            }
            // call calcualting fragment
            val gp = gpCalculator()
            (activity!! as MainActivity).navigateToView(CalculatingFrag().apply {
                calculatedGp = gp
            })
            true
        }

        course_frag_recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = LevelAdapter().apply {
                adapterUser = this@CoursesFragment
            }
        }

    }

    override fun onDestroy() {
        (activity as MainActivity).toggleFAB()
        super.onDestroy()
    }

    private fun initVibrator(){
        vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context?.getSystemService(Vibrator::class.java)
        } else {
            context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        }


    }

    private fun render(){
        course_frag_recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun gpCalculator() : Float{
        return 5.0f
    }

    private fun highlight(view: View): Unit {
        view.setBackgroundColor(Color.parseColor("#D9D9D9"))
    }

    private fun unhighlight(view : View){
        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    private fun showDeleteBtn(view: View): Unit {
        val deleteBtn = view.findViewById<ImageButton>(R.id.level_view_delete)
        deleteBtn.visibility = View.VISIBLE
    }

}
