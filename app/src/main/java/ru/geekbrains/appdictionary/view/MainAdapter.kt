package ru.geekbrains.appdictionary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.appdictionary.R

class MainAdapter() :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<ru.geekbrains.model.Meanings> = arrayListOf()

    fun setData(data: List<ru.geekbrains.model.Meanings>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: ru.geekbrains.model.Meanings) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text =
                    data.partOfSpeech.toString()
                val arrDefinitions = arrayListOf<String>()
                data.definitions?.forEach { definitionItem -> arrDefinitions.add(definitionItem.definition.toString()) }
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    arrDefinitions.joinToString(separator = " ••• ")
            }
        }
    }
}