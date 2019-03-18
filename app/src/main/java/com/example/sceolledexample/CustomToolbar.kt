package com.example.sceolledexample

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup

class CustomToolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_custom_toolbar, null)
        addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}