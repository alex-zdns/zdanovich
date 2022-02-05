package ru.zdanovich.developerslife.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadGif(
    gifUrl: String,
    previewURL: String? = null
) {
    Glide.with(this)
        .load(gifUrl)
        //.placeholder(R.drawable.ic_launcher_foreground)
        .thumbnail(Glide.with(this).load(previewURL))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}