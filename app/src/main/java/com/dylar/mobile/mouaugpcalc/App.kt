package com.dylar.mobile.mouaugpcalc

import android.app.Application
import android.content.Context

class App : Application() {
    private lateinit var _daosession : DaoSession
    val daoSession : DaoSession
        get() = this._daosession

    override fun onCreate() {
        super.onCreate()
        val helper = DaoMaster.DevOpenHelper(this, "calc-db")
        val db = helper.writableDb
        _daosession = DaoMaster(db).newSession()
        installGradeDefaults()
    }

    private fun installGradeDefaults(){
        val sharedPreference = getSharedPreferences("Grades", Context.MODE_PRIVATE)
        if(sharedPreference.getBoolean("grades_installed", false)){
            with(sharedPreference.edit()){
                putInt("A", 5)
                putInt("B", 4)
                putInt("C", 3)
                putInt("D", 2)
                putInt("E", 1)
                putInt("F", 0)
                putBoolean("grades_installed", true)
                apply()
            }
        }
    }
}