package com.pslyp.restapi

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var addButton: Button
    lateinit var removeButton: Button
    lateinit var formLayout: LinearLayout

    lateinit var textView: TextView
    lateinit var form: LinearLayout

    lateinit var removeImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstance()
    }

    fun initInstance() {
        addButton = findViewById(R.id.add_button)
        removeButton = findViewById(R.id.remove_button)
        formLayout = findViewById(R.id.form_layout)

        textView = findViewById(R.id.text_view_1)

        addButton.setOnClickListener(addFormLayout)
        removeButton.setOnClickListener(removeFormLayout)
    }

    var addFormLayout = View.OnClickListener { view ->
        var selectCheckBox = CheckBox(this)
        var nameEditText = EditText(this)
        var valueEditText = EditText(this)
        removeImage = ImageView(this)

        nameEditText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        nameEditText.hint = "name"

        valueEditText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        valueEditText.hint = "value"

        removeImage.setImageResource(R.drawable.ic_close_black_24dp)
        removeImage.setOnClickListener(removeFormLayout)

        form = LinearLayout(this)
        form.addView(selectCheckBox)
        form.addView(nameEditText)
        form.addView(valueEditText)
        form.addView(removeImage)

        formLayout.addView(form)
    }

    var removeFormLayout = View.OnClickListener { view ->

//        var parent: ViewGroup = form.parent as ViewGroup
//        var index: Int = parent.indexOfChild(form)

        var index: Int = formLayout.indexOfChild(form)

        var index2 = form.indexOfChild(removeImage)
        Toast.makeText(this, index2.toString(), Toast.LENGTH_LONG).show()

        if(formLayout.childCount != 0) {
            formLayout.removeViewAt(0)
            textView.text = index.toString()
        }
    }
}
