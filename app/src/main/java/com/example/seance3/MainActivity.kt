package com.example.seance3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        adapter = DataAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        performNetworkCall()

    }

    private fun performNetworkCall() {

        val client = OkHttpClient()


        val url = URL("https://example.com/api")
        val request = Request.Builder().url(url).build()
        client
            .newCall(request)
            .enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("OKHTTP", "OnFailure: ${e.localizedMessage}")
            }
            override fun onResponse(call: Call, response: Response) {
                Log.i("OKHTTP", "OnSuccess")
                Log.i("OKHTTP", response.networkResponse.toString())
            }
        })

    }

}