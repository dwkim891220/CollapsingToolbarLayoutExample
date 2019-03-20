package com.example.sceolledexample

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.layout_custom_toolbar.view.*

class ScrollingActivity : AppCompatActivity(), ActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setLayouts()
    }

    private fun setLayouts() {
        setToolbar()
        setBottomNavigationView()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        collapseBar.title = "TThis iis TTitle"

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)

        rv_image.adapter = ImageAdapter()
        rv_image.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)

        var viewToolbar = layoutInflater.inflate(R.layout.layout_custom_toolbar, null)
        supportActionBar?.setCustomView(
            viewToolbar,
            ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
        )

        appBar.addOnOffsetChangedListener(
            @RequiresApi(Build.VERSION_CODES.M)
            object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                    when (state) {
                        State.Expanded -> {
                            viewToolbar.iv_navi.isEnabled = true
                            Toast.makeText(this@ScrollingActivity, "expanded", Toast.LENGTH_SHORT).show()
                            var flag = window.decorView.systemUiVisibility
                            flag = flag and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                            window.decorView.systemUiVisibility = flag
                            window.statusBarColor = 0x00000000
                        }
                        State.Collapsed -> {
                            viewToolbar.iv_navi.isEnabled = false
                            Toast.makeText(this@ScrollingActivity, "collapsed", Toast.LENGTH_SHORT).show()
                            var flag = window.decorView.systemUiVisibility
                            flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            window.decorView.systemUiVisibility = flag
                            window.statusBarColor = 0xff00ff
                        }
                        else -> return
                    }
                }
            }
        )
    }

    private fun setBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
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
                    replaceFragment(StartupFragment.TAG)
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

    private fun replaceFragment(tag: String) {
        val fragment = when (tag) {
            HomeFragment.TAG -> HomeFragment.newInstance()
            MapFragment.TAG -> MapFragment.newInstance()
            StartupFragment.TAG -> StartupFragment.newInstance()
            else -> return
        }

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fl_mainContainer,
                fragment,
                tag
            )
            .commit()
    }

    override fun toolbarExpand(need: Boolean) {
        appBar.setExpanded(need, true)
    }

    override fun enbleAppbarBehavior(enable: Boolean) {
        ((appBar.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior as? DisableableAppBarLayoutBehavior)?.mEnabled = enable

    }
}

interface ActivityListener {
    fun toolbarExpand(need: Boolean)
    fun enbleAppbarBehavior(enable: Boolean)
}
