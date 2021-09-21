package com.example.editingpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.Comment
import com.example.editingpost.Model.ModifyComment
import com.example.editingpost.Preferences.Preferences
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_editing_comment : AppCompatActivity() {
    companion object {
        const val CONST_POST_ID_COMMENT = "post_id"
        const val CONST_REPLY_TO_COMMENT = "reply_to_comment"
    }

 // lateinit var text_user_id: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_editing_comment)
        getTextComment()
    }



    fun deleteComment(view: View){
        Common.retrofitService.deleteComment(intent.getIntExtra(CONST_POST_ID_COMMENT, 0),"Bearer ${Preferences(this).getToken()}")
            .enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    finish()
                }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                }

            })
    }




    fun getTextComment() {
        Common.retrofitService.getComment(intent.getIntExtra(MainActivity_editing_comment.CONST_POST_ID_COMMENT, 0), "Bearer ${Preferences(this).getToken()}").enqueue(object: Callback<List<Comment>> {
                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                }
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                }

            })
    }

    fun editingComment(view: View){
        val text_editing_comment: EditText = findViewById(R.id.text_editing_comment)
        val modifyComment = ModifyComment(text_editing_comment.text.toString())
        Common.retrofitService.modifyComment(modifyComment, intent.getIntExtra(CONST_REPLY_TO_COMMENT, 0),"Bearer ${Preferences(this).getToken()}").enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                finish()
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                finish()
            }
        })
    }



}
