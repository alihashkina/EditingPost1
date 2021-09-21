package com.example.editingpost.Model

data class Comment (
    var text: String,
var post_id: Int,
var reply_to_comment: Int
)