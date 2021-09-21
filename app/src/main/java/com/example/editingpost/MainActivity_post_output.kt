package com.example.editingpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.editingpost.Adapter.MyAdapter
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.PostOutput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_post_output : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_post_output)


    }

    fun postAddbt(view: View){
        this.startActivity(Intent(this, MainActivity_add_post::class.java))
    }

    override fun onResume(){
        super.onResume()
        Common.retrofitService.getPostOutputList().enqueue(object : Callback<List<PostOutput>> {
            override fun onResponse(call: Call<List<PostOutput>>, response: Response<List<PostOutput>>) {
                onLoadPosts(response.body()!!)
            }

            override fun onFailure(call: Call<List<PostOutput>>, t: Throwable) {

            }
        })
    }


    fun onLoadPosts(postOutput:List<PostOutput>){
        val adapter = MyAdapter(this,postOutput)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPostOutputList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}