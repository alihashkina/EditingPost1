package com.example.editingpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.editingpost.Adapter.MyCommentAdapter
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.Comment
import com.example.editingpost.Preferences.Preferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_add_comment : AppCompatActivity() {

    companion object {
        const val CONST_POST_COMMENT_ADD = "post_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_add_comment)
    }

    fun addComment(view: View) {
        addCommentText()
    }

    fun addCommentText() {
        val text_add_comment: EditText = findViewById(R.id.text_add_comment)
        val comment = Comment(text_add_comment.text.toString(), 0, 0)

        Common.retrofitService.postComment(comment, "Bearer ${Preferences(this).getToken()}").enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
            //  this.startActivity(Intent(this, MainActivity_comment::class.java))
              //  onLoadComment(response.body()!!)
                finish()
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                onError()
            }
        })
    }

   /* fun onLoadComment(comment:List<Comment>){
        val adapter = MyCommentAdapter(this,comment)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerComment)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }*/

    fun onError(){

    }

}