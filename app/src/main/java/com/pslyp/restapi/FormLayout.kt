package com.pslyp.restapi

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.iterator

class FormLayout(context: Context, layout: LinearLayout) {

    private val layout: LinearLayout
    private val item: LinearLayout
    private val checkbox: CheckBox
    private val name: EditText
    private val value: EditText
    private val remove: ImageView

    init {
        this.layout = layout
        item = LinearLayout(context)
        checkbox = CheckBox(context)
        name = EditText(context)
        value = EditText(context)
        remove = ImageView(context)
    }

    fun create(): FormLayout {
        item.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        item.orientation = LinearLayout.HORIZONTAL

        checkbox.isChecked = true

        name.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        name.hint = "name"

        value.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        value.hint = "value"

        remove.setImageResource(R.drawable.ic_close_black_24dp)
        remove.setOnClickListener(removeListener)

        item.addView(checkbox)
        item.addView(name)
        item.addView(value)
        item.addView(remove)

        layout.addView(item)

        return this
    }

    private var removeListener = View.OnClickListener { view ->
        var index = layout.indexOfChild(item)

        if(layout.childCount != 0) {
            layout.removeViewAt(index)
        }
    }

    fun getParams(): HashMap<String,String> {
        val params = HashMap<String,String>()
        for (view in layout.iterator()) {
            val childs = (view as ViewGroup)
            val checked = (childs.getChildAt(0) as CheckBox).isChecked
            val name = (childs.getChildAt(1) as EditText).text.toString().trim()
            val value = (childs.getChildAt(2) as EditText).text.toString().trim()

            if(checked)
                params.put(name, value)
        }
        return params
    }

}