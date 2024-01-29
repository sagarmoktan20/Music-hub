package com.example.musichub

import android.content.Context

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musichub.models.songModel

object MexoPlayer {
    private var exoPlayer:ExoPlayer?=null
    private var currentSong:songModel?=null

    fun getCurrentSong():songModel?{
        return currentSong
    }
    fun getInstance():ExoPlayer?{
        return exoPlayer
    }
    fun startPlaying(context: Context,song:songModel){
        if (exoPlayer==null)
            exoPlayer=ExoPlayer.Builder(context).build()
        if(currentSong!=song){
            currentSong=song
            currentSong?.url?.apply {
                val madiaItem=MediaItem.fromUri(this)
                exoPlayer?.setMediaItem(madiaItem)
                exoPlayer?.prepare()
                exoPlayer?.play()
            }
        }
    }
}