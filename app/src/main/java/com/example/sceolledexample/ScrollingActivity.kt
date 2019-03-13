package com.example.sceolledexample

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        app_bar.addOnOffsetChangedListener(
            object : AppBarStateChangeListener(){
                override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                    when(state){
                        State.Expanded -> {
                            fl_tb.setBackgroundColor(Color.TRANSPARENT)
                            iv_tb_back.setImageResource(R.drawable.icon_navi_back_w)
                            iv_tb_search.setImageResource(R.drawable.icon_navi_search_w)
                        }
                        State.Collapsed -> {
                            fl_tb.setBackgroundColor(Color.WHITE)
                            iv_tb_back.setImageResource(R.drawable.icon_navi_back)
                            iv_tb_search.setImageResource(R.drawable.icon_navi_search)
                        }
                    }
                }
            }
        )
    }
}
