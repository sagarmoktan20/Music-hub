package com.example.musichub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musichub.adapter.Songs_list_adapter
import com.example.musichub.databinding.ActivityMainBinding
import com.example.musichub.databinding.ActivitySongsListBinding
import com.example.musichub.models.CategoryModel

class songsListActivity : AppCompatActivity() {
    lateinit var binding: ActivitySongsListBinding
    lateinit var songsListAdapter: Songs_list_adapter
    companion object{
        lateinit var category: CategoryModel

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySongsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nameTv.text= category.name
        Glide.with(binding.coverImgView).load(category.coverUrl)
            .apply {
                RequestOptions().transform(RoundedCorners(32))
            }
            .into(binding.coverImgView)

        setUpSongListRecyclerView()

    }
    fun setUpSongListRecyclerView(){
    songsListAdapter= Songs_list_adapter(category.songs)
        binding.songListRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.songListRecyclerView.adapter=songsListAdapter

    }
}