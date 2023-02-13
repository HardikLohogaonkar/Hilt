package com.ibm.hilt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibm.hilt.databinding.ItemMovieListBinding
import com.ibm.hilt.model.Movie

class MovieListAdapter : ListAdapter<Movie, ViewHolderMovie>(MovieDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {
        val itemMovieListBinding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderMovie(itemMovieListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        val item = getItem(position)
        holder.itemMovieListBinding.movie = item
    }
}

class ViewHolderMovie(val itemMovieListBinding: ItemMovieListBinding) :
    RecyclerView.ViewHolder(itemMovieListBinding.root)

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}