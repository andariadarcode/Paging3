package com.andariadar.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andariadar.paging3.data.Person
import com.andariadar.paging3.databinding.ItemPersonBinding

class PersonPagingAdapter: PagingDataAdapter<Person, PersonPagingAdapter.PersonViewHolder>(PersonComparator) {
    inner class PersonViewHolder(private val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.apply {
                tvPerson.text = person.name
                tvText.text = person.text
            }
        }
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    object PersonComparator: DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}

