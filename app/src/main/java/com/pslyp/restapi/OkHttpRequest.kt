package com.pslyp.restapi

import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpRequest(client: OkHttpClient) {

    private val client: OkHttpClient

    init {
        this.client = client
    }

    fun GET(url: String, callback: Callback) {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request).enqueue(callback)
        return call
    }

    fun POST(url: String, params: HashMap<String,String>, callback: Callback) {
        val builder = FormBody.Builder()
        val item = params.entries.iterator()
        while (item.hasNext()) {
            val pair = item.next() as Map.Entry<*, *>
            builder.add(pair.key.toString(), pair.value.toString())
        }

        val formBody = builder.build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val call = client.newCall(request).enqueue(callback)
        return call
    }

}