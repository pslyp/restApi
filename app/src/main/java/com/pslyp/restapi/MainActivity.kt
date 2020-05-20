package com.pslyp.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.core.view.iterator
import com.google.android.material.textfield.TextInputLayout
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var addButton: Button
    lateinit var textView: TextView
    lateinit var formLayout: LinearLayout

    lateinit var methodSpinner: Spinner
    lateinit var httpSpinner: Spinner
    lateinit var urlText: TextInputLayout

    lateinit var requestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInstance()
    }

    fun initInstance() {
        addButton = findViewById(R.id.add_button)
        formLayout = findViewById(R.id.form_layout)

        textView = findViewById(R.id.text_view_1)

        addButton.setOnClickListener(addForm)

        methodSpinner = findViewById(R.id.method_spinner)
        httpSpinner = findViewById(R.id.http_spinner)
        urlText = findViewById(R.id.url_text_input)

        requestButton = findViewById(R.id.request_button)

        val methods: ArrayList<String> = ArrayList()
        methods.add("GET")
        methods.add("POST")
        methods.add("PUT")
        methods.add("DELETE")
        methods.add("PATCH")

        val methodAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, methods)
        methodSpinner.adapter = methodAdapter

        val https: ArrayList<String> = ArrayList()
        https.add("HTTP")
        https.add("HTTPS")
        https.add("HTTP2")
        https.add("AUTO")

        val httpAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, https)
        httpSpinner.adapter = httpAdapter

        requestButton.setOnClickListener(request)
    }

    var addForm = View.OnClickListener { view ->
        var form = From(this, formLayout).create()

        formLayout.addView(form)
    }

    val request = View.OnClickListener { view ->
        var text: String = "Method: " + methodSpinner.selectedItem.toString() + "\n"
        text += "Url: " + httpSpinner.selectedItem.toString() + "://" + urlText.editText?.text.toString()

        Toast.makeText(this, text, Toast.LENGTH_LONG).show()

        val url = urlText.editText?.text.toString()

        val client = OkHttpClient()
        val request = OkHttpRequest(client)

//        request.GET(url, object: Callback {
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                runOnUiThread {
//                    textView.text = response.code.toString()
//
//                    Toast.makeText(this@MainActivity, response.body?.string(), Toast.LENGTH_LONG).show()
//                }
//            }
//        })

        val forms = formLayout.getChildAt(0) as ViewGroup
        val name = (forms.getChildAt(1) as EditText).text.toString().trim()
        val value = (forms.getChildAt(2) as EditText).text.toString().trim()

        val params = HashMap<String,String>()
        params.put(name, value)

        request.POST(url, params, object: Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {

            }
        })
    }

}