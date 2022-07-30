package com.example.guess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val function = listOf<String>(
        "Camera",
        "Guess game",
        "Record list",
        "Download coupons",
        "News",
        "Maps"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler View
        recycler_record_list.layoutManager = LinearLayoutManager(this)
        recycler_record_list.setHasFixedSize(true)
        recycler_record_list.adapter = FunctionAdapter()

    }
    inner class FunctionAdapter() : RecyclerView.Adapter<FuntionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuntionHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_function, parent, false)
            val holder = FuntionHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: FuntionHolder, position: Int) {
            holder.nameText.text = function.get(position)
            holder.itemView.setOnClickListener {
                functionClicked(position)
            }
        }

        override fun getItemCount(): Int {
            return function.size
        }

    }

    fun functionClicked(position: Int) {
        when (position) {
            1 -> startActivity(Intent(this, MaterialActivity::class.java))
            2 -> startActivity(Intent(this, RecordListActivity::class.java))
            else -> return
        }
    }

    class FuntionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView = view.name
    }

}