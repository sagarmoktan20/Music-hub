package com.example.musichub.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musichub.MexoPlayer
import com.example.musichub.databinding.SongListItemRecyclerRowBinding
import com.example.musichub.models.songModel
import com.example.musichub.playerActivity
import com.google.firebase.firestore.FirebaseFirestore

class Songs_list_adapter(private val songIdList:List<String>):
    RecyclerView.Adapter<Songs_list_adapter.MyViewHolder>() {

    class MyViewHolder(private var binding:SongListItemRecyclerRowBinding):
        RecyclerView.ViewHolder(binding.root){
            @SuppressLint("SuspiciousIndentation")
            fun bindData(songId:String){
          FirebaseFirestore.getInstance().collection("songs")
              .document(songId).get()
              .addOnSuccessListener {
                val song=it.toObject(songModel::class.java)
                  song?.apply {
                      binding.songTitleTextView.text=song.title
                      binding.songSubtitleTetView.text=song.subtitle
                      Glide.with(binding.songCoverImgView).load(coverurl).apply(
                          RequestOptions().transform(RoundedCorners(32))
                      ).into(binding.songCoverImgView)
                      binding.root.setOnClickListener {
                          MexoPlayer.startPlaying(binding.root.context,song)
                          it.context.startActivity(Intent(it.context,playerActivity::class.java))
                      }
                  }
              }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=SongListItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return songIdList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindData(songIdList[position])
    }
}