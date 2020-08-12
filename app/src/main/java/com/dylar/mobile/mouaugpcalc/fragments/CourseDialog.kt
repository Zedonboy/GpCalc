package com.dylar.mobile.mouaugpcalc.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Spinner
import com.dylar.mobile.mouaugpcalc.R
import com.dylar.mobile.mouaugpcalc.utils.CourseData

class CourseDialog : DialogFragment(){
    var negativeClicklistener: (view: DialogInterface) -> Unit = fun(_: DialogInterface) = this.dismiss()
    var positiveClickListener: (view: DialogInterface, data: Any) -> Unit = fun(_: DialogInterface, _ : Any) = this.dismiss()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.course_dialog_frag, null)
        return activity!!.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(view)
            builder.setPositiveButton("Save") { dialog, _ ->
                val grade = view!!.findViewById<Spinner>(R.id.levels).selectedItem as String
                val course_name = view.findViewById<EditText>(R.id.course_dialog_title).editableText.toString()
                val unitLoad = view.findViewById<Spinner>(R.id.unit_loader_spinner).selectedItem as String
                if (course_name.isEmpty()) return@setPositiveButton
                positiveClickListener(dialog, CourseData(course_name, grade, unitLoad.toInt()))
            }
            builder.setNegativeButton("Cancel"){dialog, _ ->
                negativeClicklistener(dialog)
            }

            builder.create()
        }
    }


    fun setNegativeBtnClicked(function: (view : DialogInterface) -> Unit){
        negativeClicklistener = function
    }

    fun setPostiveBtnCLicked(function: (view : DialogInterface, data : Any) -> Unit): Unit {
        positiveClickListener = function
    }

}