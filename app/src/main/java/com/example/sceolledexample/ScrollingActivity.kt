package com.example.sceolledexample

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), ActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setLayouts()
    }

    private fun setLayouts(){
        setToolbar()
        setBottomNavigationView()
    }

    private fun setToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        appBar.addOnOffsetChangedListener(
            object : AppBarStateChangeListener(){
                override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                    when(state){
                        State.Expanded -> {
                            fl_tb.setBackgroundColor(Color.TRANSPARENT)
                            iv_tb_back.setImageResource(R.drawable.icon_navi_back_w)
                        }
                        State.Collapsed -> {
                            fl_tb.setBackgroundColor(Color.WHITE)
                            iv_tb_back.setImageResource(R.drawable.icon_navi_back)
                        }
                        else -> return
                    }
                }
            }
        )
    }

    private fun setBottomNavigationView(){
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment.TAG)
                    true
                }
                R.id.bottom_favorite -> {
                    replaceFragment(MapFragment.TAG)
                    false
                }
                R.id.bottom_map -> {
                    replaceFragment(MapFragment.TAG)
                    true
                }
                R.id.bottom_4 -> {
                    replaceFragment(MapFragment.TAG)
                    true
                }
                R.id.bottom_more -> {
                    replaceFragment(MapFragment.TAG)
                    true
                }
                else -> false
            }
        }

        replaceFragment(HomeFragment.TAG)
    }

    private fun replaceFragment(tag: String){
        val fragment = when(tag){
            HomeFragment.TAG -> HomeFragment.newInstance()
            MapFragment.TAG -> MapFragment.newInstance()
            else -> return
        }

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fl_mainContainer,
                fragment,
                tag)
            .commit()
    }

    override fun toolbarExpand(need: Boolean) {
        appBar.setExpanded(need, false)
    }
}

interface ActivityListener{
    fun toolbarExpand(need: Boolean)
}
