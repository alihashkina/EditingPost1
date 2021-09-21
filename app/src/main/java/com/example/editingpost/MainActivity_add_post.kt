package com.example.editingpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.Post
import com.example.editingpost.Preferences.Preferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_add_post : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_add_post)
    }

    fun addPost(view: View) {
        addPostText()
    }

    fun addPostText() {
        val text_add_post: EditText = findViewById(R.id.text_add_post)
        val post = Post(text_add_post.text.toString())

        Common.retrofitService.getPostList(post, "Bearer ${Preferences(this).getToken()}").enqueue(object:
            Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                finish()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                onError()
            }
        })
    }

    fun onError(){

    }

}