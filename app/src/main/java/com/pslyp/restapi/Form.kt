package com.pslyp.restapi

import android.content.Context
import android.view.View
import android.widget.*

class From(context: Context, layout: LinearLayout): LinearLayout(context) {

    private val checkBox: CheckBox
    private val nameEditText: EditText
    private val valueEditText: EditText
    private val removeImage: ImageView

    init {
        checkBox = CheckBox(context)
        nameEditText = EditText(context)
        valueEditText = EditText(context)
        removeImage = ImageView(context)
    }

    fun create(): LinearLayout {
        nameEditText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        nameEditText.hint = "name"

        valueEditText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        valueEditText.hint = "value"

        removeImage.setImageResource(R.drawable.ic_close_black_24dp)
        removeImage.setOnClickListener(remove)

        this.addView(checkBox)
        this.addView(nameEditText)
        this.addView(valueEditText)
        this.addView(removeImage)

        return this
    }

    private var remove = View.OnClickListener { view ->
        var index = layout.indexOfChild(this)

        if(layout.childCount != 0) {
            layout.removeViewAt(index)
        }
    }

}