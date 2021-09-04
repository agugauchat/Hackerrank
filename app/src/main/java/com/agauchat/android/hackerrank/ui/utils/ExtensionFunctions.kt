package com.agauchat.android.hackerrank.ui.utils

import android.widget.ImageView
import com.agauchat.android.hackerrank.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.load(url: String?) {

    url?.let {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.placeholder_image)
            .placeholder(R.drawable.placeholder_image)
            .into(this)
    }
}