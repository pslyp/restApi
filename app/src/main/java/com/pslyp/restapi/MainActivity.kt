package com.pslyp.restapi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import okhttp3.*
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

        requestButton.setOnClickListener{view ->

            var text: String = "Method: " + methodSpinner.selectedItem.toString() + "\n"
            text += "Url: " + httpSpinner.selectedItem.toString() + "://" + urlText.editText?.text.toString()

            Toast.makeText(this, text, Toast.LENGTH_LONG).show()

            val client = OkHttpClient()

            val url: String = urlText.editText?.text.toString()

            val request: Request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
//                    textView.text = response.code.toString()

//                    Log.e("Request", response.code.toString())
//                    Log.e("ฺBody", response.body?.string())

                    if(response.code == 200) {
                        this@MainActivity.runOnUiThread(Runnable {
//                            setText(response)
                            Toast.makeText(this@MainActivity, response.body?.string(), Toast.LENGTH_LONG).show()
                        })
//                        setText(response)
                    }
                }
            })
        }
    }

    var addForm = View.OnClickListener { view ->
        var form: LinearLayout = From(this, formLayout).create()

        formLayout.addView(form)
    }

    fun setText(res: Response) {
        textView.text = res.body?.string()
    }

//    fun GET(url: String): Response {
//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//                .url(url)
//                .build()
//
//        client.newCall(request).enqueue(object: Callback {
//
//            override fun onFailure(call: Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//
//            }
//
//        })
//    }

}