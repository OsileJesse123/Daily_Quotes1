package com.hfad.dailyquotes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.dataClass.CategoriesClass
import com.hfad.dailyquotes.mainActivity.MainActivity

class CategoriesAdapter(
    private val list: List<CategoriesClass>,
    private val onClickListener: (name: String) -> Unit
):
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categories_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val currentPosition = list[position]

        holder.text.text = currentPosition.getTitl()
        holder.img.setImageResource(currentPosition.getImg())

        holder.itemView.setOnClickListener {

            onClickListener(currentPosition.getTitl())
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val text :TextView = itemView.findViewById(R.id.category_title)
        val img :ImageView = itemView.findViewById(R.id.category_img)


    }
}