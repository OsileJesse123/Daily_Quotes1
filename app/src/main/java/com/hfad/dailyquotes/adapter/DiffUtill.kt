package com.hfad.dailyquotes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hfad.dailyquotes.dataClass.Quotedataclass

class DiffUtill : DiffUtil.ItemCallback<Quotedataclass>() {
    override fun areContentsTheSame(oldItem: Quotedataclass, newItem: Quotedataclass)
            = (oldItem.id == newItem.id)

    override fun areItemsTheSame(oldItem: Quotedataclass, newItem: Quotedataclass) = (oldItem == newItem)


}