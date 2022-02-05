package ru.zdanovich.developerslife.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadGif(
    gifUrl: String,
    previewURL: String? = null
) {
    val requestOptions = RequestOptions()

    Glide.with(this)
        .load(gifUrl)
        .thumbnail(Glide.with(this).load(previewURL))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(requestOptions.centerCrop())
        .into(this)
}