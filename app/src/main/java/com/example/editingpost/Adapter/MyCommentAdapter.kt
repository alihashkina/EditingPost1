package com.example.editingpost.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.editingpost.MainActivity_add_comment
import com.example.editingpost.MainActivity_comment
import com.example.editingpost.MainActivity_editing_comment
import com.example.editingpost.Model.Comment
import com.example.editingpost.R

class MyCommentAdapter(private val context: Context, private val comment: List<Comment>) : RecyclerView.Adapter<MyCommentAdapter.MyCommentViewHolder>() {
    class MyCommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val text_comment : TextView = itemView.findViewById(R.id.text_comment)
        val post_id_comment: TextView = itemView.findViewById(R.id.post_id_comment)
        val reply_to_comment: TextView = itemView.findViewById(R.id.reply_to_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return MyCommentViewHolder(itemView)
    }

    override fun getItemCount() = comment.size

    override fun onBindViewHolder(holder: MyCommentViewHolder, position: Int) {
        val commentItem = comment[position]

        holder.text_comment.text = commentItem.text
        holder.post_id_comment.text = commentItem.post_id.toString()
        holder.reply_to_comment.text = commentItem.reply_to_comment.toString()

        holder.itemView.setOnClickListener{
            val intentC = Intent(context, MainActivity_editing_comment::class.java)
            intentC.putExtra(MainActivity_editing_comment.CONST_POST_ID_COMMENT,commentItem.post_id)
            intentC.putExtra(MainActivity_editing_comment.CONST_REPLY_TO_COMMENT,commentItem.reply_to_comment)
            holder.itemView.context.startActivity(intentC)

       /*     val intentAddCom = Intent(context, MainActivity_add_comment::class.java)
            intentAddCom.putExtra(MainActivity_add_comment.CONST_POST_COMMENT_ADD,commentItem.text)
            holder.itemView.context.startActivity(intentAddCom)*/
        }
    }
}