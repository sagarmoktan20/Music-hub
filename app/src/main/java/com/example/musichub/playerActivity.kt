package com.example.musichub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.musichub.databinding.ActivityPlayerBinding

class playerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    lateinit var exoPlayer: ExoPlayer
    var playerListener=object :Player.Listener{
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            showGif(isPlaying)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MexoPlayer.getCurrentSong()?.apply {
            binding.songTitleTextView.text=title
            binding.songSubtitleTetView.text=subtitle
            Glide.with(binding.songCoverImgView).load(coverurl)
                .circleCrop()
                .into(binding.songCoverImgView)

            Glide.with(binding.songGifImgView).load(R.drawable.giphy)
                .circleCrop()
                .into(binding.songGifImgView)
            exoPlayer=MexoPlayer.getInstance()!!
            binding.playerView.player=exoPlayer
            binding.playerView.showController()
            exoPlayer.addListener(playerListener)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
         exoPlayer?.removeListener(playerListener)
    }
    fun showGif(show:Boolean){
        if (show){
            binding.songGifImgView.visibility=View.VISIBLE
        }else{
            binding.songGifImgView.visibility=View.INVISIBLE
        }
    }
}