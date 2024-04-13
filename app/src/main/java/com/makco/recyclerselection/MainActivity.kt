package com.makco.recyclerselection

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tracker: SelectionTracker<Long>

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

        tracker = SelectionTracker.Builder(
            "selection-1",
            recyclerView,
            StableIdKeyProvider(recyclerView),
            ItemLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        savedInstanceState?.let {
            tracker?.onRestoreInstanceState(it)
        }

        adapter.setTracker(tracker)

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    val nItems: Int? = tracker?.selection?.size()
                    nItems?.let {
                        if (it > 0) {
                            title = "$it items selected"
                            supportActionBar?.setBackgroundDrawable(
                                ColorDrawable(getColor(R.color.Orange_Salmon))
                            )
                        } else {
                            title = "RecyclerSelection"
                            supportActionBar?.setBackgroundDrawable(
                                ColorDrawable(getColor(R.color.purple_500))
                            )
                        }
                    }
                }
            })
    }
}