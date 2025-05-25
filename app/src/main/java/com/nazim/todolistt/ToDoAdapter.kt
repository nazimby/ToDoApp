package com.nazim.todolistt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nazim.todolistt.R.id.todoItems

class ToDoAdapter(private val toDoList: ArrayList<ModelClass>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(todoItems)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ToDoViewHolder,
        position: Int
    ) {

        val item = toDoList[position]
        holder.textView.text = item.text
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = toDoList[position].isDone

        if(item.isDone){
            holder.textView.paintFlags = holder.textView.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            holder.textView.paintFlags = holder.textView.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->

            toDoList[position].isDone = isChecked
            if(isChecked){
                holder.textView.paintFlags = holder.textView.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                holder.textView.paintFlags = holder.textView.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

        }

        holder.deleteButton.setOnClickListener {
            toDoList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, toDoList.size)
        }

        holder.itemView.setOnClickListener {
            holder.checkBox.isChecked = !holder.checkBox.isChecked
        }



    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}
