package com.example.guess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guess.data.Record
import kotlinx.android.synthetic.main.row_function.view.*
import kotlinx.android.synthetic.main.row_record.view.*

class RecordAdapter(var records : List<Record>) : RecyclerView.Adapter<RecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_record, parent, false)
        val holder = RecordViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.nameText.text = records.get(position).nickname
        holder.counterText.text = records.get(position).counter.toString()
    }

    override fun getItemCount(): Int {
        return records.size
    }
}
class RecordViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var nameText : TextView = view.record_nickname
    var counterText : TextView = view.record_counter
}