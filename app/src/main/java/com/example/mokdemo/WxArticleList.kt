package com.example.mokdemo

data class WxArticleList(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)