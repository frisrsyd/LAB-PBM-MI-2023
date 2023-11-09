package com.frisrsyd.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.frisrsyd.login.model.Materi

class ListMateriAdapter (private val listMateri: ArrayList<Materi>) : RecyclerView.Adapter<ListMateriAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_materi, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMateri.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, status, level, chapters, img) = listMateri[position]
        holder.tvTitle.text = title
        holder.tvStatus.text = status
        holder.tvLevel.text = level
        holder.tvChapters.text = chapters
        Glide.with(holder.itemView.context).load(img).into(holder.imgPhoto)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMateri[holder.adapterPosition])
        }

    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tv_title_materi)
        val tvStatus : TextView = itemView.findViewById(R.id.tv_status_materi)
        val tvLevel : TextView = itemView.findViewById(R.id.tv_level_materi)
        val tvChapters : TextView = itemView.findViewById(R.id.tv_chapters_materi)
        val imgPhoto : ImageView = itemView.findViewById(R.id.img_materi)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Materi)
    }
}