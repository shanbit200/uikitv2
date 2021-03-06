package io.agora.agorauikit.ui

import android.content.Context
import android.util.AttributeSet
import io.agora.rtc.RtcEngine
import io.agora.rtc.mediaio.AgoraTextureView
import io.agora.rtc.mediaio.MediaIO

class AgoraTextureVideoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AgoraViewContainer(context, attrs, defStyleAttr) {

    private var texture: AgoraTextureView = AgoraTextureView(context)
    private var uid: Int = 0

    init {
        texture.init(null)
        texture.setBufferType(MediaIO.BufferType.BYTE_ARRAY)
        texture.setPixelFormat(MediaIO.PixelFormat.I420)
        addView(texture)
    }

    fun setMirror(engine: RtcEngine, mirror: Boolean) {
        texture.setMirror(mirror)
        setupVideo(engine)
    }

    fun setUid(engine: RtcEngine, uid: Int) {
        this.uid = uid
        setupVideo(engine)
    }

    private fun setupVideo(engine: RtcEngine) {
        if (uid == 0) {
            engine.setLocalVideoRenderer(texture)
        } else {
            engine.setRemoteVideoRenderer(uid, texture)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width: Int = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int = MeasureSpec.getSize(heightMeasureSpec)
        texture.layout(0, 0, width, height)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}