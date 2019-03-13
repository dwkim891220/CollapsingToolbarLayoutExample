package com.example.sceolledexample

import android.support.design.widget.AppBarLayout


abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {

    private var mCurrentState = State.Idle
    private var mPreviousPosition = 0

    enum class State {
        Expanded,
        Collapsed,
        Idle
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, position: Int) {
        val absPosition = Math.abs(position)

        if (absPosition == 0) {
            if (mCurrentState != State.Expanded) {
                onStateChanged(appBarLayout, State.Expanded)
            }
            mCurrentState = State.Expanded
        } else if (absPosition >= appBarLayout.totalScrollRange) {
            if (mCurrentState != State.Collapsed) {
                onStateChanged(appBarLayout, State.Collapsed)
            }
            mCurrentState = State.Collapsed
        } else {
            if (mCurrentState != State.Idle) {
                onStateChanged(
                    appBarLayout,
                    if(absPosition < mPreviousPosition) {
                        State.Expanded
                    }else{
                        State.Idle
                    }
                )
            }
            mCurrentState = State.Idle
        }

        mPreviousPosition = absPosition
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}