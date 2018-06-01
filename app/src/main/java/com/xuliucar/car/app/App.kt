package com.xuliucar.car.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig

class App : Application() {

    val isFlag = false

    override fun onCreate() {
        super.onCreate()

        val config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)
    }
}