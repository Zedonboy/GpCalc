package com.dylar.mobile.mouaugpcalc.utils

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dylar.mobile.mouaugpcalc.Interfaces.IAdapterUser

class LevelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //private var _adapeterUser : IAdapterUser? = null
    var adapterUser : IAdapterUser? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return adapterUser?.adapterCreateView(p0, p1)!!
    }

    override fun getItemCount(): Int {
        return adapterUser?.adapterGetCount()!!
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        adapterUser?.adapterOnBindVIew(p0, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return  adapterUser?.adapterGetItemType(position)!!
    }
}