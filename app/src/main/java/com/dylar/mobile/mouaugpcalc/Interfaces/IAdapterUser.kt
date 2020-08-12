package com.dylar.mobile.mouaugpcalc.Interfaces

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

interface IAdapterUser {
    fun adapterCreateView(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun adapterOnBindVIew(viewHolder: RecyclerView.ViewHolder, p0 : Int)
    fun adapterGetCount():Int
    fun adapterGetItemType(pos: Int): Int {
        throw NotImplementedError("The method adapterGetItemType is not implemented by derived")
    }
}