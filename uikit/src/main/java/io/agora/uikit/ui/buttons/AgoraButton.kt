package io.agora.uikit.ui.buttons

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.view.marginLeft
import androidx.core.view.setPadding
import io.agora.uikit.R
import io.agora.uikit.ui.DPToPx

abstract class AgoraButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        background = context.getDrawable(R.drawable.button_background)
        scaleType = ScaleType.CENTER_INSIDE
        setPadding(DPToPx(context, 15))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setMargin(R.dimen.button_margin, R.dimen.button_margin, R.dimen.button_margin, R.dimen.button_margin)
    }

    private fun View?.setMargin(
        @DimenRes marginStart: Int? = null,
        @DimenRes marginTop: Int? = null,
        @DimenRes marginEnd: Int? = null,
        @DimenRes marginBottom: Int? = null
    ) {
        setMarginPixelOffset(
            marginStart?.let {
                this?.resources?.getDimensionPixelOffset(it)
            },
            marginTop?.let {
                this?.resources?.getDimensionPixelOffset(it)
            },
            marginEnd?.let {
                this?.resources?.getDimensionPixelOffset(it)
            },
            marginBottom?.let {
                this?.resources?.getDimensionPixelOffset(it)
            }
        )
    }

    private fun View?.setMarginPixelOffset(
        marginStart: Int? = null,
        marginTop: Int? = null,
        marginEnd: Int? = null,
        marginBottom: Int? = null
    ) {

        (this?.layoutParams as? ViewGroup.MarginLayoutParams)?.let { mlp ->
            mlp.setMargins(
                marginStart ?: mlp.marginStart,
                marginTop ?: mlp.topMargin,
                marginEnd ?: mlp.marginEnd,
                marginBottom ?: mlp.bottomMargin
            )
        }
    }
}