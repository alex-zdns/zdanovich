package ru.zdanovich.developerslife.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.zdanovich.developerslife.R

fun ImageView.loadGif(
    gifUrl: String
) {
    val requestOptions = RequestOptions()

    val circularProgressDrawable = CircularProgressDrawable(this.context).apply {
        strokeWidth = resources.getDimension(R.dimen.image_placeholder_stroke_width)
        centerRadius = resources.getDimension(R.dimen.image_placeholder_center_radius)
        setColorSchemeColors(ContextCompat.getColor(context, R.color.placeholder))
        start()
    }

    Glide.with(this)
        .load(gifUrl)
        .placeholder(circularProgressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(requestOptions.centerCrop())
        .into(this)
}