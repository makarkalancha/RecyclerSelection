package com.makco.recyclerselection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val myList = mutableListOf(
        TaskItem("Get a Haircut", "Keep Hair Short"),
        TaskItem("Go to The Park", "Take Tubby With You"),
        TaskItem("Buy Some Apples", "Make Sure They are Fresh"),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RVAdapter(myList)
        recyclerView.adapter = adapter
    }
}