package com.dylar.mobile.mouaugpcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.View
import com.dylar.mobile.mouaugpcalc.fragments.LevelFragment
import com.dylar.mobile.mouaugpcalc.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private lateinit var _daosession : DaoSession
    val daoSession : DaoSession
    get() = this._daosession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
        navigateToView(LevelFragment())
    }

    private fun init() {
        setupDatabase()
        fab.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? LevelFragment
            fragment?.showDialogBox()
        }

        toolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.settings -> {
                    val frag = supportFragmentManager.findFragmentById(R.id.fragment_container)
                    if(frag is SettingsFragment) return@setOnMenuItemClickListener true
                    val settings = SettingsFragment()
                    navigateToView(settings)
                }
            }
            true
        }
    }

    private fun setupDatabase(){
        _daosession = (application as App?)?.daoSession ?: throw IllegalStateException("Application cannot be null")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    fun navigateToView(data : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, data, data.toString())
                .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }
        supportFragmentManager.popBackStack()
    }

    fun changeToolBarTitle(title : String){
        val toolbar = supportActionBar
        toolbar?.title = title
    }

    fun toggleFAB(){
        fab?.visibility = if(fab!!.visibility == View.INVISIBLE) View.VISIBLE else View.INVISIBLE
    }

    fun switchOffFAB(){
        fab?.visibility = View.INVISIBLE
    }

    fun switchOnFAB(){
        fab?.visibility = View.VISIBLE
    }
}
