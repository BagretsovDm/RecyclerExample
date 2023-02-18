package com.example.recyclerexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerexample.databinding.ItemBinding

class Adapter(private var list: List<Model>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setList(newList: List<Model>) {
        list = newList
        notifyItemInserted(list.lastIndex)
    }

    class MyViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Model) {
            with(binding) {
                firstCount.text = model.first.toString()
                secondCount.text = model.second.toString()
                position.text = "Position $adapterPosition"
            }
        }
    }
}
