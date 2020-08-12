package com.dylar.mobile.mouaugpcalc.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dylar.mobile.mouaugpcalc.MainActivity
import com.dylar.mobile.mouaugpcalc.R
import com.dylar.mobile.mouaugpcalc.Semester
import com.dylar.mobile.mouaugpcalc.SemesterDao
import com.dylar.mobile.mouaugpcalc.utils.CustomViewHolder
import com.dylar.mobile.mouaugpcalc.utils.LevelAdapter
import kotlinx.android.synthetic.main.fragment_level.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SemesterFragment : LevelFragment() {
    private val dataContainer = mutableListOf<Semester>()
    var currLevel = ""
    override fun adapterGetCount(): Int {
        return dataContainer.size
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initUI()
        (activity as MainActivity).changeToolBarTitle("Semesters")
    }
    override fun adapterOnBindVIew(viewHolder: RecyclerView.ViewHolder, p0: Int) {
        if (viewHolder is CustomViewHolder){
            val view = viewHolder.itemView
            view.setOnClickListener {
                val controller = activity as MainActivity
                controller.navigateToView(CoursesFragment())
            }
            val semesterName = view.findViewById<TextView>(R.id.course_view_level_name)
            val gp_score = view.findViewById<TextView>(R.id.level_view_gp_score)
            val gp_name = view.findViewById<TextView>(R.id.level_view_gp_name)
            kotlin.run {
                semesterName.text = "${dataContainer[p0].level} - ${dataContainer[p0].semester} semester"
                semesterName.textSize = 15f
                gp_name.text = dataContainer[p0].gpName
                gp_score.text = dataContainer[p0].gp.toString()
            }
        }
    }

    private fun initUI(){
        fragment_level_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = LevelAdapter().apply {
                adapterUser = this@SemesterFragment
            }
        }
        getData()
        (activity as MainActivity).changeToolBarTitle("Level")
    }
    override fun getData() = launch{
        val activiti = activity as MainActivity
            val levelDao = activiti.daoSession.semesterDao
            val record: MutableList<Semester>
            try {
                val query = levelDao.queryBuilder()
                        .where(SemesterDao.Properties.Level.eq(currLevel))
                        .build()
                record = query.list()
            }
            catch (e : Exception){
                return@launch
            }
            if (record.isEmpty()) return@launch
            dataContainer.clear()
            dataContainer.addAll(record)
            withContext(Dispatchers.Main) {
                fragment_level_recyclerView?.adapter?.notifyDataSetChanged()
            }
    }

    override fun toString(): String = "SemesterFragment"

    override fun showDialogBox() {
        // work on Semester Fragment Here
        val dialog = SemesterDialog()
        dialog.setPostiveBtnCLicked { _, data ->
            if (data is String){
                val dick = activity!! as MainActivity
                val semester = Semester().apply {
                    semester = data
                    level = currLevel
                }
                try {
                    dick.daoSession.semesterDao.insert(semester)
                    getData()
                } catch (e : Exception){
                    dialog.dismiss()
                } finally {
                    dialog.dismiss()
                }
            }
        }

        dialog.show(childFragmentManager, "semester_dialog")
    }
}