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

class MainActivity_comment : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_comment)
    }

    fun button_add_commentclk(view: View){
        this.startActivity(Intent(this, MainActivity_add_comment::class.java))
    }

    override fun onResume(){
        super.onResume()
        Common.retrofitService.getComment(intent.getIntExtra(MainActivity_editing_comment.CONST_POST_ID_COMMENT, 0), "Bearer ${Preferences(this).getToken()}").enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                onLoadComment(response.body()!!)
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

            }
        })
    }


    fun onLoadComment(comment:List<Comment>){
        val adapter = MyCommentAdapter(this,comment)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerComment)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}