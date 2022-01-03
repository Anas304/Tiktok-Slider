package com.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.network.model.Video
import kotlinx.android.synthetic.main.item_single_video.view.*

class ViewPagerVideoAdapter(private val videos: List<Video>) :
    RecyclerView.Adapter<ViewPagerVideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_single_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentVideo = videos[position]
        holder.bindVideotoSource(currentVideo)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindVideotoSource(video: Video) {
            itemView.videoView.apply {
                setVideoPath(video.video_url)
                setOnPreparedListener { mp ->
                    mp.start()
                    val videoRatio: Float = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
                    val screenRatio: Float = this.width.toFloat() / this.height.toFloat()
                    val scale = videoRatio / screenRatio
                    if (scale >= 1F) {
                        this.scaleX = scale
                    } else {
                        this.scaleY = scale
                    }
                }
                setOnCompletionListener {
                    it.start()
                }
            }
        }
    }
}