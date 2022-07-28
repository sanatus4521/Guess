package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = FunctionAdapter()
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
        }

        override fun getItemCount(): Int {
            return function.size
        }

    }

    class FuntionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView = view.name
    }

}