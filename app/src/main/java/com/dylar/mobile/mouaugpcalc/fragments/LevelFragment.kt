package com.dylar.mobile.mouaugpcalc.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.dylar.mobile.mouaugpcalc.*
import com.dylar.mobile.mouaugpcalc.Interfaces.IAdapterUser
import com.dylar.mobile.mouaugpcalc.utils.CustomViewHolder
import com.dylar.mobile.mouaugpcalc.utils.LevelAdapter
import kotlinx.android.synthetic.main.fragment_level.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class LevelFragment : Fragment(), IAdapterUser, CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
    private val dataContainer = mutableListOf<Level>()
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).switchOnFAB()
    }
    override fun adapterCreateView(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ViewType.NORMAL_VIEW -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.level_view, parent, false)
                CustomViewHolder(view, ViewType.NORMAL_VIEW)
            }
            ViewType.OVERALL_GP_VIEW -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.overall_gp_view, parent, false)
                CustomViewHolder(view, ViewType.OVERALL_GP_VIEW)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.level_view, parent, false)
                CustomViewHolder(view, ViewType.NORMAL_VIEW)
            }
        }
    }

    override fun adapterOnBindVIew(viewHolder: RecyclerView.ViewHolder, p0: Int) {
        if(viewHolder is CustomViewHolder){
            when(viewHolder.viewType){
                ViewType.NORMAL_VIEW -> {
                    val view = viewHolder.itemView
                    val level_name = view.findViewById<TextView>(R.id.course_view_level_name)
                    val gp_score = view.findViewById<TextView>(R.id.level_view_gp_score)
                    val gp_name = view.findViewById<TextView>(R.id.level_view_gp_name)
                    val deleteBtn = view.findViewById<ImageButton>(R.id.level_view_delete)
                    run {
                        view.setOnClickListener {
                            if (view.tag == true){
                                unhighlight(it)
                                view.tag = false
                                return@setOnClickListener
                            }
                            val controller = activity!! as MainActivity
                            controller.navigateToView(SemesterFragment().apply {
                                currLevel = dataContainer[p0].level
                            })
                        }
                        level_name.text = dataContainer[p0].level
                        gp_score.text = dataContainer[p0].gp.toString()
                        gp_name.text = dataContainer[p0].gpName
                        deleteBtn.setOnClickListener {
                            val controller = activity as MainActivity
                            val level = controller.daoSession.levelDao
                            val semester = controller.daoSession.semesterDao
                            launch {
                                level.queryBuilder()
                                        .where(LevelDao.Properties.Level.eq(dataContainer[p0].level))
                                        .buildDelete()
                                        .executeDeleteWithoutDetachingEntities()
                                semester.queryBuilder()
                                        .where(SemesterDao.Properties.Level.eq(dataContainer[p0].level))
                                        .buildDelete()
                                        .executeDeleteWithoutDetachingEntities()
                                (activity as MainActivity).daoSession.clear()
                                getData()
                            }

                        }
                        view.setOnLongClickListener {
                            highlight(it)
                            showDeleteBtn(it)
                            view.tag = true
                            true
                        }
                    }
                }

                ViewType.OVERALL_GP_VIEW -> {
                    // calculate cgpa
                    val view = viewHolder.itemView
                    val over_all_textView = view.findViewById<TextView>(R.id.overall_gp_score)
                    // do the calculation math for CGPA
                    over_all_textView.text = "5.0" // placeholder for Now
                }
            }

        }
    }

    override fun adapterGetItemType(pos: Int): Int {
        return if(dataContainer.size > 0 && pos == dataContainer.size){
            ViewType.OVERALL_GP_VIEW
        } else {
            ViewType.NORMAL_VIEW
        }
    }

    override fun adapterGetCount(): Int {
        if (dataContainer.size == 0) return dataContainer.size
        return dataContainer.size + 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }
    private fun initUI(){
        fragment_level_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = LevelAdapter().apply {
                adapterUser = this@LevelFragment
            }
        }
        getData()
        (activity as MainActivity).changeToolBarTitle("Level")
    }

     open fun getData() = launch{
        val activiti = activity as MainActivity
         val levelDao = activiti.daoSession.levelDao
         val record: MutableList<Level>
         try {
             record = levelDao.loadAll()
         }
         catch (e : Exception){
             return@launch
         }

         dataContainer.clear()
         dataContainer.addAll(record)
         withContext(Dispatchers.Main){
             fragment_level_recyclerView?.adapter?.notifyDataSetChanged()
         }
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

    override fun toString(): String = "LevelFragment"

    open fun showDialogBox(){
        val dialog = LevelDialog()
        dialog.setPostiveBtnCLicked { _, data ->
            if (data is String){
                val pussy = activity as MainActivity
                val level = Level().apply {
                    level = data
                }
                try {
                    pussy.daoSession.levelDao.insert(level)
                } catch (e : Exception){
                    dialog.dismiss()
                } finally {
                    dialog.dismiss()
                    getData()
                }
            }
        }
        dialog.show(activity!!.supportFragmentManager, "level_dialog")
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }

    private class ViewType {

        companion object {
            const val NORMAL_VIEW = 0
            const val OVERALL_GP_VIEW = 1
        }
    }
}