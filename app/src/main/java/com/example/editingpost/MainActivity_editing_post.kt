package com.example.editingpost

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.editingpost.Adapter.MyAdapter
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.Auth
import com.example.editingpost.Model.ModifyPost
import com.example.editingpost.Model.PostOutput
import com.example.editingpost.Preferences.Preferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_editing_post : AppCompatActivity() {

    companion object {
        const val CONST_POST_ID = "id"
        const val CONST_USER_ID = "user_id"
    }

    lateinit var text_user_id: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_editing_post)
        getTextPost()
        getUserId()
    }

    fun getUserId(){
        text_user_id = findViewById(R.id.text_user_id)
        Common.retrofitService.getUserId(intent.getIntExtra(CONST_USER_ID, 0),"Bearer ${Preferences(this).getToken()}")
            .enqueue(object : Callback<PostOutput> {
                override fun onResponse(call: Call<PostOutput>, response: Response<PostOutput>) {
                    text_user_id.text = intent.getIntExtra(CONST_USER_ID, 0).toString()
                }
                override fun onFailure(call: Call<PostOutput>, t: Throwable) {
                }

            })
    }

    fun deletePost(view: View){
        Common.retrofitService.deletePost(intent.getIntExtra(CONST_POST_ID, 0),"Bearer ${Preferences(this).getToken()}")
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    finish()
                }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                }

            })
    }




        fun getTextPost() {
        Common.retrofitService.getPostInf(intent.getIntExtra(CONST_POST_ID, 0))
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                 }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                }

            })
    }

    fun editingPost(view: View){
        val text_editing_post: EditText = findViewById(R.id.text_editing_post)
        val modifyPost = ModifyPost(text_editing_post.text.toString())
        Common.retrofitService.modifyPost(modifyPost, intent.getIntExtra(CONST_POST_ID, 0),"Bearer ${Preferences(this).getToken()}").enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                finish()
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                finish()
            }
        })
    }

  /*  fun addCommentclk(view: View){
        this.startActivity(Intent(this, MainActivity_add_comment::class.java))
    }*/

    fun lookComment(view: View){
        this.startActivity(Intent(this, MainActivity_comment::class.java))
    }

    fun bt123(view: View){
        this.startActivity(Intent(this, MainActivity_add_comment::class.java))

    }

    }







