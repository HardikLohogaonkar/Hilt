package com.ibm.hilt.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.ibm.hilt.network.NetworkApiConstants.BASE_PATH_IMAGE

@BindingAdapter("imageUrl", "error")
fun loadImage(imageView: ImageView, url: String, error: Drawable) {

    imageView.load(BASE_PATH_IMAGE + url) {
        crossfade(true)
        crossfade(500)
        placeholder(error)
    }
}