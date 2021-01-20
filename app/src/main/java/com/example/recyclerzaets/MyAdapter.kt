package com.example.recyclerzaets

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerzaets.databinding.ViewLayoutBinding

class MyAdapter(private val items: List<Person>): RecyclerView.Adapter<MyViewHolder>() {
    private var padding = 50

    private fun countDistance(startPerson: Person, lookingFor: Person, distance: Int = 0): Int {
        if (startPerson == lookingFor) return distance

        if (startPerson.mother != null) {
            var tempDistance = countDistance(startPerson.mother, lookingFor, distance + 1)
            if (tempDistance > 0) return tempDistance
        }
        if (startPerson.father != null) {
            var tempDistance = countDistance(startPerson.father, lookingFor, distance + 1)
            if (tempDistance > 0) return tempDistance
        }

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position], countDistance(items[0], items[position])*padding)
    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ViewLayoutBinding

    fun bind(person: Person, padding: Int) {
        binding = ViewLayoutBinding.bind(itemView)

        binding.layout.setPadding(padding, binding.layout.paddingTop, binding.layout.paddingRight, binding.layout.paddingBottom)
        binding.name.text = person.name
        binding.age.text = person.age.toString()
    }
}