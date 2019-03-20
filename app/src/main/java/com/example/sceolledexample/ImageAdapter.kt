package com.example.sceolledexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.list_cell_image.view.*

class ImageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return DefaultHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_cell_image, p0, false))
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        when(holder) {
            is DefaultHolder -> {
                if(p1 == 0) {
                    holder.itemView.iv_image.setImageResource(R.drawable.img_rolling_safe)
                } else {
                    holder.itemView.iv_image.setImageResource(R.drawable.icon_test)
                    holder.itemView.iv_image.scaleType = ImageView.ScaleType.FIT_CENTER
                }

            }
        }
    }

    inner class DefaultHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}