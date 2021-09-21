package com.example.editingpost.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.editingpost.MainActivity_editing_post
import com.example.editingpost.MainActivity_post_output
import com.example.editingpost.Model.PostOutput
import com.example.editingpost.R

class MyAdapter(private val context: Context, private val postOutput: List<PostOutput>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id_postOutput : TextView = itemView.findViewById(R.id.id_post_output)
        val text_postOutput: TextView = itemView.findViewById(R.id.text_post_output)
        val user_id_postOutput: TextView = itemView.findViewById(R.id.user_id_post_output)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_output, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = postOutput.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postOutputItem = postOutput[position]

        holder.id_postOutput.text = postOutputItem.id.toString()
        holder.text_postOutput.text = postOutputItem.text
        holder.user_id_postOutput.text = postOutputItem.user_id.toString()

        holder.itemView.setOnClickListener{
            val intent = Intent(context, MainActivity_editing_post::class.java)
            intent.putExtra(MainActivity_editing_post.CONST_POST_ID,postOutputItem.id)
            intent.putExtra(MainActivity_editing_post.CONST_USER_ID,postOutputItem.user_id)
            holder.itemView.context.startActivity(intent)
        }
    }
}