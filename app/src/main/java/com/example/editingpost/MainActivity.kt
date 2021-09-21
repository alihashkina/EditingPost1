package com.example.editingpost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.editingpost.Common.Common
import com.example.editingpost.Model.Reg
import com.example.editingpost.Model.Token
import com.example.editingpost.Preferences.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun start(){
        if (!Preferences(this).getToken().isNullOrEmpty()){
            this.startActivity(Intent(this, MainActivity_post_output::class.java))
        }
    }


    override fun onResume(){
        super.onResume()
        start()
    }


    fun authenticationClick(view: View){
        this.startActivity(Intent(this, MainActivity_authentication::class.java))
    }


    fun register(view: View) {
        val txt_email: EditText = findViewById(R.id.txt_email)
        val txt_name: EditText = findViewById(R.id.txt_name)
        val txt_password: EditText = findViewById(R.id.txt_password)
        val password_confirmation: EditText = findViewById(R.id.txt_password2)
        val button_reg: Button = findViewById(R.id.button_reg)
        val reg = Reg(txt_name.text.toString(),txt_email.text.toString(),txt_password.text.toString(),password_confirmation.text.toString())

        Common.retrofitService.getRegList(reg).enqueue(object : Callback<Token> {
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
            Preferences(this@MainActivity).setToken(token.token)
            this.startActivity(Intent(this, MainActivity_post_output::class.java))
        }else{
            Toast.makeText(this, "123123123", Toast.LENGTH_SHORT).show()
        }

    }
    fun onError(){

    }
}