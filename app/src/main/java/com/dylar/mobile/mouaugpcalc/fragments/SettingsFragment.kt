package com.dylar.mobile.mouaugpcalc.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.dylar.mobile.mouaugpcalc.MainActivity
import com.dylar.mobile.mouaugpcalc.R
import kotlinx.android.synthetic.main.settings_frag.*

class SettingsFragment : Fragment() {
    private val gradeList : List<String> = listOf("A", "B", "C", "D", "E", "F")
    private val map : MutableMap<String, Int> = mutableMapOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.settings_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(activity as MainActivity){
            switchOffFAB()
            changeToolBarTitle("Settings")
        }
        initUI()
    }

    private fun initUI(): Unit {
        save_settings?.setOnClickListener {
            map.forEach { entry ->
                val preference =  activity!!.getSharedPreferences("Grades", Context.MODE_PRIVATE)
                with(preference.edit()){
                    putInt(entry.key, entry.value)
                    apply()
                }
            }
        }

        getDefaultGradesPts()
        updateSeekBars()

        seekBar_gradeA?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gradeA_text?.text = "Grade A point = ${progress}"
                map["A"] = progress
            }

        })
        seekBar_gradeB?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gradeB_text?.text = "Grade B point = ${progress}"
                map["B"] = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seekBar_gradeC?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gradeC_text?.text = "Grade C point = ${progress}"
                map["C"] = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        seekBar_gradeD?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gradeD_text?.text = "Grade D point = ${progress}"
                map["D"] = progress
            }

        })
        seekBar_gradeE?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gradeE_text?.text = "Grade E point = ${progress}"
                map["E"] = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seekBar_gradeF?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gradeF_text?.text = "Grade F point = ${progress}"
                map["F"] = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun getDefaultGradesPts(){
        val starter = 6
        val sharedPrefrences = activity!!.getSharedPreferences("Grades", Context.MODE_PRIVATE)
        for (item in gradeList){
            map[item] = sharedPrefrences.getInt(item, starter.dec())
        }
    }

    private fun updateSeekBars(){
        for(key in map.keys){
            when(key){
                "A" -> {
                    seekBar_gradeA?.progress = map[key]!!
                }
                "B" -> {
                    seekBar_gradeB?.progress = map[key]!!
                }
                "C" -> {
                    seekBar_gradeC?.progress = map[key]!!
                }
                "D" -> {
                    seekBar_gradeD?.progress = map[key]!!
                }
                "E" -> {
                    seekBar_gradeE?.progress = map[key]!!
                }
                "F" -> {
                    seekBar_gradeF?.progress = map[key]!!
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).switchOnFAB()
    }
}