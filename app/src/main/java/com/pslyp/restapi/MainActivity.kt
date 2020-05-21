package com.pslyp.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var forms: FormLayout? = null

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
        forms = FormLayout(this, formLayout)

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
        forms?.create()
    }

    val request = View.OnClickListener { view ->
        var text = "Method: " + methodSpinner.selectedItem.toString() + "\n"
        text += "Url: " + httpSpinner.selectedItem.toString().toLowerCase() + "://" + urlText.editText?.text.toString()

        Toast.makeText(this, text, Toast.LENGTH_LONG).show()

        val url = httpSpinner.selectedItem.toString().toLowerCase() + "://" + urlText.editText?.text.toString()

        val client = OkHttpClient()
        val request = OkHttpRequest(client)

        val methodPosition = methodSpinner.selectedItemPosition
        when(methodPosition) {
            0 -> request.GET(url, object: Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        textView.text = response.code.toString()

                        Toast.makeText(this@MainActivity, response.body?.string(), Toast.LENGTH_LONG).show()
                    }
                }
            })
            1 -> forms?.getParams()?.let {
                request.POST(url, it, object: Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {

                    }
                })
            }
        }

    }

}