package com.dylar.mobile.mouaugpcalc.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Spinner
import com.dylar.mobile.mouaugpcalc.R
import kotlinx.android.synthetic.main.level_dialog_frag.*

 open class LevelDialog : DialogFragment() {
    protected var negativeClicklistener: (view: DialogInterface) -> Unit = fun(_: DialogInterface) = this.dismiss()
    protected var positiveClickListener: (view: DialogInterface, data: Any) -> Unit = fun(_: DialogInterface, _ : Any) = this.dismiss()

     override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         val view = LayoutInflater.from(activity).inflate(R.layout.level_dialog_frag, null)
         return activity?.let {
             val builder = AlertDialog.Builder(it)
             builder.setView(view)
             builder.setPositiveButton("Save") { dialog, _ ->
                 val value = view!!.findViewById<Spinner>(R.id.levels).selectedItem as String
                 positiveClickListener(dialog, value)
             }
             builder.setNegativeButton("Cancel"){dialog, _ ->
                 negativeClicklistener(dialog)
             }
             builder.create()
         } ?: throw IllegalStateException("Activity cannot be null")
     }

    open fun setNegativeBtnClicked(function: (view : DialogInterface) -> Unit){
        negativeClicklistener = function
    }

    open fun setPostiveBtnCLicked(function: (view : DialogInterface, data : Any) -> Unit): Unit {
        positiveClickListener = function
    }
}