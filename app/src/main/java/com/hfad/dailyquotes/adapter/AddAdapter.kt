package com.hfad.dailyquotes.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.dailyquotes.R
import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.viewModels.QuoteViewModel

class AddAdapter(val  c: Context, private val onClickListener: (quote: Quotedataclass) -> Unit, val viewModel: QuoteViewModel)
    : ListAdapter<Quotedataclass, AddAdapter.UserViewHolder>(DiffUtill()) {

    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var title2: TextView = view.findViewById(R.id.quote_title)
        var content: TextView = view.findViewById(R.id.quote_texts)
        private var menus: ImageView = view.findViewById(R.id.menu_icon)


        init {
            menus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v: View) {
            val positions = getItem(adapterPosition)
            val position = adapterPosition
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.edit_text ->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_dialog, null)
                        val name =v.findViewById<EditText>(R.id.user)
                        val number = v.findViewById<EditText>(R.id.userInput)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog, _ ->
                                positions.quoteAuthor = name.text.toString()
                                positions.quoteText = number.text.toString()
                                viewModel.editQuote(positions)
                                notifyItemChanged(position)
                                Toast.makeText(c, "Edit Successful", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("Cancel"){
                                    dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete_text ->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setMessage("Are you sure you want to delete this Quote")
                            .setPositiveButton("Sure"){
                                    dialog, _ ->
                                 onClickListener(positions)
                                Toast.makeText(c, "Quote deleted", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true

                    }
                    else -> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.add_list, parent, false)
        return UserViewHolder(v)
    }



    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = getItem(position)
        holder.title2.text = newList.quoteAuthor
        holder.content.text = newList.quoteText
    }

}