package com.example.sceolledexample

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class StartupFragment : Fragment() {
    private var listener: ActivityListener? = null

    companion object {
        val TAG = StartupFragment::class.java.simpleName

        fun newInstance(): StartupFragment {
            return StartupFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? ActivityListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_startup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.run {
            toolbarExpand(false)
            enbleAppbarBehavior(false)
        }
    }
}