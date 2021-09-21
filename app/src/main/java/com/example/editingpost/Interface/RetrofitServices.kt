package com.example.editingpost.Interface

import com.example.editingpost.Model.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface RetrofitServices {
    @POST("register")
    fun getRegList(@Body reg: Reg): Call<Token>

    @GET("post")
    fun getPostOutputList(): Call<List<PostOutput>>

    @POST("token")
    fun Auth(@Body auth: Auth): Call<Token>

    @POST("post")
    fun getPostList(@Body post: Post, @Header("Authorization") token:String): Call<JsonObject>


    @PATCH("post/{id}")
    fun modifyPost(@Body modifyPost: ModifyPost, @Path("id") id:Int, @Header("Authorization") token:String) : Call<JsonObject>

    @GET("post/{id}")
    fun getPostInf(@Path("id")id:Int) : Call<JsonObject>

    @DELETE("post/{id}")
    fun deletePost(@Path("id")id:Int, @Header("Authorization") token:String) : Call<JsonObject>

    @GET("user/{id}")
    fun getUserId(@Path("id")id:Int, @Header("Authorization") token:String) : Call<PostOutput>

    @POST("comment")
    fun postComment(@Body comment: Comment, @Header("Authorization") token:String): Call<List<Comment>>

    @GET ("comment/{id}")
    fun getComment(@Path("id")id:Int, @Header("Authorization") token:String) : Call<List<Comment>>


    @DELETE ("comment/{id}")
    fun deleteComment(@Path("id")id:Int, @Header("Authorization") token:String) : Call<JsonObject>

    @PATCH ("comment/{id}")
    fun modifyComment(@Body modifyComment: ModifyComment, @Path("id") id:Int, @Header("Authorization") token:String) : Call<JsonObject>

    @GET ("post/{id}/comments")
    fun getPostComment()


}