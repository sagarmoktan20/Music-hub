package com.example.musichub.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musichub.R
import com.example.musichub.databinding.CategoryItemRecyclerRowBinding
import com.example.musichub.models.CategoryModel
import com.example.musichub.songsListActivity


class CategoryAdapter(private val categoryList:List<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: CategoryItemRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bindData(category:CategoryModel){
                binding.nameTv.text=category.name
                Glide.with(binding.coverImgView).load(category.coverUrl)
                    .apply {
                        RequestOptions().transform(RoundedCorners(42))
                    }
                    .into(binding.coverImgView)
                Log.i("songs",category.songs.size.toString())

                //start song list activity
                val context=binding.root.context
                binding.root.setOnClickListener {
                    songsListActivity.category=category
                    context.startActivity(Intent(context,songsListActivity::class.java))
                }

            }
        }
    //here binding . root means the views of  CategoryItemRecyclerRowBinding.xml layout


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     val binding=CategoryItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
   return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     holder.bindData(categoryList[position])
    }
}