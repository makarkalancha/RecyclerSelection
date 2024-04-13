package com.makco.recyclerselection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class TaskItem(val title: String, val description: String)

class RVAdapter(private val listItems: List<TaskItem>) : RecyclerView.Adapter<RVAdapter.TaskViewHolder>() {

    class TaskViewHolder(todoTaskView: View) : RecyclerView.ViewHolder(todoTaskView) {
        val title: TextView = todoTaskView.findViewById(R.id.task_title)
        val description: TextView = todoTaskView.findViewById(R.id.task_detail)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = listItems.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.todo_task, viewGroup, false)
        return TaskViewHolder(v)
    }

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
        taskViewHolder.title.text = listItems[position].title
        taskViewHolder.description.text = listItems[position].description
    }
}
