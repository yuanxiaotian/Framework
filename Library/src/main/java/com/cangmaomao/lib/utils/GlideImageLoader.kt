package com.cangmaomao.lib.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        val uri = Uri.parse(path as String?)
        if (imageView != null) {
            imageView.setImageURI(uri)
        }
    }

    override fun createImageView(context: Context?): ImageView {
        return SimpleDraweeView(context)
    }
}