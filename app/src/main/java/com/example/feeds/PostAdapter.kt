package com.example.feeds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val context: Context, postList: MutableList<Item>) : RecyclerView.Adapter<PostAdapter.PostHolder>() {
    private val mList: MutableList<Item> = postList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val mView = LayoutInflater.from(context).inflate(R.layout.imageitem, parent, false)
        return PostHolder(mView)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val item = mList[position]
        holder.setImageView(item.imageurl)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)

        fun setImageView(url: String) {
            Glide.with(context).load(url).into(imageView)
        }
    }
}
