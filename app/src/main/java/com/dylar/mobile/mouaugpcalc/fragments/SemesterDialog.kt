package com.dylar.mobile.mouaugpcalc.fragments

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.dylar.mobile.mouaugpcalc.Level
import com.dylar.mobile.mouaugpcalc.MainActivity
import com.dylar.mobile.mouaugpcalc.R
import com.dylar.mobile.mouaugpcalc.Semester
import kotlinx.android.synthetic.main.level_dialog_frag.*

class SemesterDialog : LevelDialog() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.semester_dialog_frag, null)
        return activity!!.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(view)
            builder.setPositiveButton("Save") { dialog, _ ->
                val value = view!!.findViewById<Spinner>(R.id.semesters).selectedItem as String
                positiveClickListener(dialog, value)
            }
            builder.setNegativeButton("Cancel"){dialog, _ ->
                negativeClicklistener(dialog)
            }
            builder.create()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}