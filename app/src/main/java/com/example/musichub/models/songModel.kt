package com.example.musichub.models

import android.icu.text.CaseMap.Title

data class songModel(
    val id:String,
    val title: String,
    val subtitle:String,
    val url:String,
    val coverurl:String
)
{
    constructor():this("","","","","")
}
