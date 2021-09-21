package com.example.editingpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.Auth
import com.example.editingpost.Model.Token
import com.example.editingpost.Preferences.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_authentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_authentihication)
    }


    fun authentication(view: View) {
        val txt_email_authentication: EditText = findViewById(R.id.txt_email_authentication)
        val txt_password_authentication: EditText = findViewById(R.id.txt_password_authentication)
        val button_authentication: Button = findViewById(R.id.button_authentication)
        val auth = Auth(txt_email_authentication.text.toString(),txt_password_authentication.text.toString())

        Common.retrofitService.Auth(auth).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                saveToken(response.body())
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                onError()
            }
        })
    }
    fun saveToken(token: Token?){
        if(token != null){
            Preferences(this@MainActivity_authentication).setToken(token.token)
            this.startActivity(Intent(this, MainActivity_post_output::class.java))
        }else{
            Toast.makeText(this, "123123123", Toast.LENGTH_SHORT).show()
        }

    }
    fun onError(){
        Toast.makeText(this, "123123123", Toast.LENGTH_SHORT).show()
    }
}